package edu.upbc.beducoffee.api

import edu.upbc.beducoffee.model.ProductData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServices {
    @GET("products/{product_name}.json")
    fun getProduct(@Path("product_name") productName: String): Call<ProductData>
}