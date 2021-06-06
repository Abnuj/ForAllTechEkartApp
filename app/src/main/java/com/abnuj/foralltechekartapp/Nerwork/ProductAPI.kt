package com.abnuj.foralltechekartapp.Nerwork

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

object Utils {
    const val BASE_URL = "https://fakestoreapi.com/"
}

interface ProductAPI {

    @GET("products")
    suspend fun getallProducts(): Response<List<ProductModel>>

    @GET("products/{id}")
    suspend fun getSingleProduct(@Path("id") userId: Int = 1):Response<ProductModel>
}