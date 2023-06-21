package org.bedu.cafebedu

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import org.bedu.cafebedu.api.api
import org.bedu.cafebedu.model.ProductData
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.bedu.cafebedu.databinding.ActivityProductSelBinding

val donutCard = CardFragment()
val cartFragment = CartFragment()
val DONUT_KEY = "DONUT_KEY"
val COFFEE_KEY = "COFFEE_KEY"
val LATITUDE_KEY = "LATITUDE_KEY"
val LONGITUD_KEY = "LONGITUD_KEY"


class ProductSelActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductSelBinding
    lateinit var fusedLocationClient: FusedLocationProviderClient

    companion object{
        const val PERMISSION_ID = 33
    }

@SuppressLint("SuspiciousIndentation")
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProductSelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setCurrentFragment1(donutCard)
        createFragments()

        getProductData("donuts")
        getProductData("coffee")

        getLocation()

        binding.carrito.setOnClickListener {
            setCurrentFragment1(cartFragment)

        }

    }

    private fun setCurrentFragment1(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun createFragments() {
        binding.bottomNavigationView.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    setCurrentFragment1(donutCard)
                    it.actionView?.clearFocus()
                    true
                }
                R.id.nav_history -> {
                    setCurrentFragment1(cartFragment)
                    it.actionView?.clearFocus()
                    true
                }
            }
        }
    }
    private fun getProductData(productName: String) {
        api.endpoint.getProduct(productName).enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                if (response.isSuccessful) {
                    response.body()?.let { productData ->
                        val price = productData.price?.toFloat() ?: 0.0f
                        if (productName == "donuts") {
                            preferences.edit()
                                .putFloat(DONUT_KEY, price)
                                .apply()
                        }
                        else {
                            preferences.edit()
                                .putFloat(COFFEE_KEY, price)
                                .apply()
                        }
                       // Toast.makeText(this@ProductSelActivity, "The price is $price", Toast.LENGTH_LONG).show()
                    } ?: run {
                        productNotFoundResponse(this@ProductSelActivity, productName)
                    }
                } else {
                    showNetworkError(this@ProductSelActivity, response.toString())
                }
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                Toast.makeText(this@ProductSelActivity, "Error processing your request. Please try again later: ${t.message.toString()}", Toast.LENGTH_SHORT).show()
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

    private fun checkGranted(permission: String): Boolean{
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
    }

    //Revisamos permisos del Manifest
    private fun checkPermissions() =
        checkGranted(android.Manifest.permission.ACCESS_COARSE_LOCATION) &&
                checkGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)


    //Pedir los permisos requeridos para que funcione la localización
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION, android.Manifest.permission.ACCESS_FINE_LOCATION),
            PERMISSION_ID
        )
    }

    //Revisamos si el GPS esta prendido
    private fun isLocationEnabled(): Boolean {
        var locationManager: LocationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    val latitude = location.latitude.toFloat()
                    val longitud = location.longitude.toFloat()

                    preferences.edit()
                        .putFloat(LATITUDE_KEY, latitude)
                        .putFloat(LONGITUD_KEY, longitud)
                        .apply()
                }
            }
            else {
                //Aquí va el reto 1 ver solucion se hace con un intent
                Toast.makeText(this, "GPS is off", Toast.LENGTH_SHORT).show()}
        }
        else {
            requestPermissions()
        }
    }

}

