package com.abnuj.foralltechekartapp.Nerwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofit by lazy {
            Retrofit.Builder().baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }


        /// now we get aour ProductApi class for Ekart app
        val api: ProductAPI by lazy {
            retrofit.create(ProductAPI::class.java)
        }

    }
}