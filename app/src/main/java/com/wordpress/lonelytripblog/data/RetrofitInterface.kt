package com.wordpress.lonelytripblog.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.food2fork.com/"

interface RetrofitInterface {
    @GET("api/search")
    fun getListOfRecipes(@Query("key") apiKey: String): Call<AnswerFromInternet>
}