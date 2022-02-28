package com.example.task.network

import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

class Api {
    var apiInterface: ApiInterface? = null
    private val BASE_URL: String = "https://thesimplycoder.herokuapp.com/";
    init {
        buildRetrofit();
    }
    private fun buildRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        apiInterface = retrofit.create<ApiInterface>(ApiInterface::class.java)
    }
    companion object {
        private var instance: Api? = null
        fun getInstance(): Api {
            return instance ?: synchronized(this) {
                Api().also { instance = it }
            }
        }
    }
}