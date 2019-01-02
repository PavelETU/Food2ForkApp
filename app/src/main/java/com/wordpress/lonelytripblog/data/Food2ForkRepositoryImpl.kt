package com.wordpress.lonelytripblog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wordpress.lonelytripblog.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Food2ForkRepositoryImpl(private val retrofitInterface: RetrofitInterface,
                              private val stringProvider: StringProvider): Food2ForkRepository {
    override fun getRecipes(): LiveData<Result<List<Recipe>>> {
        val result = MutableLiveData<Result<List<Recipe>>>()
        retrofitInterface.getListOfRecipes().enqueue(object : Callback<List<Recipe>>{
            override fun onFailure(call: Call<List<Recipe>>, t: Throwable) {
                result.value = Result.Error(stringProvider.getStringById(R.string.internet_connection_error))
            }

            override fun onResponse(call: Call<List<Recipe>>, response: Response<List<Recipe>>) {
                val responseList = response.body()
                if (!response.isSuccessful || responseList == null) {
                    result.value = Result.Error(stringProvider.getStringById(R.string.internet_connection_error))
                    return
                }
                if (responseList.isEmpty()) {
                    result.value = Result.Error(stringProvider.getStringById(R.string.no_items_error))
                } else {
                    result.value = Result.Success(responseList)
                }
            }

        })
        return result
    }

}