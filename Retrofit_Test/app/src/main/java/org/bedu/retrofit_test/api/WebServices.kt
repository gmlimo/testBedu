package org.bedu.retrofit_test.api

import org.bedu.retrofit_test.model.ProductData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServices {
    @GET("products/{product}.json")
    fun getProduct(@Path("product")product: String): Call<ProductData>
}