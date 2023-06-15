package org.bedu.retrofit_test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.bedu.retrofit_test.api.api
import org.bedu.retrofit_test.databinding.ActivityMainBinding
import org.bedu.retrofit_test.model.ProductData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with (binding) {
            searchBtn.setOnClickListener {
                val productName = productText.text.toString()
                fetchProductData(productName)
            }
        }
    }

    private fun fetchProductData(productName: String){
        api.endpoint.getProduct(productName).enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful) {
                    response.body()?.let { productData ->
                        binding.price.text = productData.price?.toString()
                    } ?: run {
                        productNotFoundResponse(this@MainActivity, productName)
                    }
                } else {
                    showNetworkError(this@MainActivity, response.toString())
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error processing your request. Please try again later: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun productNotFoundResponse(context: Context, productName: String) {
        Log.e("NotFound", "Product not found error: $productName")
        Toast.makeText(context, "Product not found: $productName\nPlease try searching for another product.", Toast.LENGTH_SHORT).show()
    }

    private fun showNetworkError(context: Context, errorMessage: String) {
        Log.e("Not200", "Error not 200: $errorMessage")
        Toast.makeText(context, "Error processing your request. Please try again later: $errorMessage", Toast.LENGTH_SHORT).show()
    }
}
