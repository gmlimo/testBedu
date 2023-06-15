package org.bedu.retrofit_test

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
                val productName = productText.getText().toString()
                val call = api.endpoint.getProduct(productName)

                call.enqueue(object : Callback<ProductData> {
                    override fun onFailure(call: Call<ProductData>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Hubo un error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                        if(response.code()==200){
                            val body = response.body()
                            Log.d("Respuesta","${response.body().toString()}")

                            binding.price.text = body?.price.toString()


                        } else{
                            Log.e("Not200","Error not 200: $response")
                            Toast.makeText(this@MainActivity, "No se encontró el producto", Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }
        }
    }
}