package com.wordpress.lonelytripblog.data

import retrofit2.Call
import retrofit2.http.GET

const val API_KEY = "Insert your own API_KEY here"
const val BASE_URL = "https://www.food2fork.com/"

interface RetrofitInterface {
    @GET("api/search?key=$API_KEY")
    fun getListOfRecipes(): Call<AnswerFromInternet>
}