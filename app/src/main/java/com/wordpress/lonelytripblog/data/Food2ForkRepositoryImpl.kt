package com.wordpress.lonelytripblog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wordpress.lonelytripblog.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Food2ForkRepositoryImpl(private val retrofitInterface: RetrofitInterface,
                              private val stringProvider: StringProvider): Food2ForkRepository {
    private val result: MutableLiveData<Result<List<Recipe>>> by lazy {
        MutableLiveData<Result<List<Recipe>>>().also { updateResultFromInternet() }
    }

    override fun getRecipes(): LiveData<Result<List<Recipe>>> {
        return result
    }

    private fun updateResultFromInternet() {
        retrofitInterface.getListOfRecipes().enqueue(object : Callback<AnswerFromInternet> {
            override fun onFailure(call: Call<AnswerFromInternet>, t: Throwable) {
                result.value = Result.Error(stringProvider.getStringById(R.string.internet_connection_error))
            }

            override fun onResponse(call: Call<AnswerFromInternet>, response: Response<AnswerFromInternet>) {
                val responseList = response.body()
                if (!response.isSuccessful || responseList == null) {
                    result.value = Result.Error(stringProvider.getStringById(R.string.internet_connection_error))
                    return
                }
                val recipes = responseList.recipes
                if (recipes.isEmpty()) {
                    result.value = Result.Error(stringProvider.getStringById(R.string.no_items_error))
                } else {
                    result.value = Result.Success(recipes)
                }
            }

        })
    }

}