package com.wordpress.lonelytripblog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wordpress.lonelytripblog.data.Food2ForkRepository
import com.wordpress.lonelytripblog.data.Recipe
import com.wordpress.lonelytripblog.data.Result

class Food2ForkViewModel(val food2ForkRepository: Food2ForkRepository) : ViewModel() {
    public fun getListOfRecipes(): LiveData<List<Recipe>?> {
        return Transformations.map(food2ForkRepository.getRecipes()) {
            when (it) {
                is Result.Error -> return@map null
                is Result.Success -> return@map it.result
            }
        }
    }

    public fun getMessageToDisplay(): LiveData<String?> {
        return Transformations.map(food2ForkRepository.getRecipes()) {
            when (it) {
                is Result.Error -> return@map it.message
                is Result.Success -> return@map null
            }
        }
    }
}