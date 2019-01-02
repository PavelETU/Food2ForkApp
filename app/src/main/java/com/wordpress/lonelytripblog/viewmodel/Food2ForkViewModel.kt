package com.wordpress.lonelytripblog.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.wordpress.lonelytripblog.data.Food2ForkRepository
import com.wordpress.lonelytripblog.data.Recipe
import com.wordpress.lonelytripblog.data.Result

open class Food2ForkViewModel(private val food2ForkRepository: Food2ForkRepository) : ViewModel() {
    open fun getListOfRecipes(): LiveData<List<Recipe>?> {
        return Transformations.map(food2ForkRepository.getRecipes()) {
            when (it) {
                is Result.Error -> return@map null
                is Result.Success -> return@map it.result
            }
        }
    }

    open fun getMessageToDisplay(): LiveData<String?> {
        return Transformations.map(food2ForkRepository.getRecipes()) {
            when (it) {
                is Result.Error -> return@map it.message
                is Result.Success -> return@map null
            }
        }
    }

    fun update() {
        food2ForkRepository.forceLoading()
    }
}