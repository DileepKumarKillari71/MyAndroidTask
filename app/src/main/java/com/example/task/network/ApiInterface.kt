package com.example.task.network

import retrofit2.http.GET
import com.example.task.network.ResponseData
import com.example.task.pojo.Hero
import retrofit2.Call

interface ApiInterface {
    // specify the sub url for our base url
    @GET("marvel-heroes")
    fun doGetListResources(): Call<ResponseData<List<Hero>>>
}