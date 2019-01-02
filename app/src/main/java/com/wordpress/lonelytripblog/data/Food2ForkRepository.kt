package com.wordpress.lonelytripblog.data

import androidx.lifecycle.LiveData

interface Food2ForkRepository {
    fun getRecipes(): LiveData<Result<List<Recipe>>>
    fun forceLoading()
}