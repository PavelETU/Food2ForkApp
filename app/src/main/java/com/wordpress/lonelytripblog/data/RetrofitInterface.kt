package com.wordpress.lonelytripblog.data

import retrofit2.Call
import retrofit2.http.GET

const val API_KEY = "Insert your API_KEY here"

interface RetrofitInterface {
    @GET("api/search?key=$API_KEY")
    fun getListOfRecipes(): Call<List<Recipe>>
}