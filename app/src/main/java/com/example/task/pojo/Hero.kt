package com.example.task.pojo

import com.google.gson.annotations.SerializedName

data class Hero(
        @field:SerializedName("_id")
        var id: String,
        @field:SerializedName("superhero_name")
        var charName: String,
        @field:SerializedName("photo")
        var photo: String,
        @field:SerializedName("name")
        var name: String)