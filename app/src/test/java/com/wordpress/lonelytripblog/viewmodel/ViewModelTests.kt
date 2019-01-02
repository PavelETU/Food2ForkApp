package com.wordpress.lonelytripblog.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wordpress.lonelytripblog.data.Food2ForkRepository
import com.wordpress.lonelytripblog.data.Recipe
import com.wordpress.lonelytripblog.data.Result
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class ViewModelTests {
    private lateinit var viewModel: Food2ForkViewModel
    private lateinit var repository: Food2ForkRepository
    private lateinit var listOfRecipesObserver: Observer<in List<Recipe>?>
    private lateinit var messageObserver: Observer<in String?>
    private lateinit var fromRepo: MutableLiveData<Result<List<Recipe>>>
    @Rule
    @JvmField
    val ruleForTestingArchitectureComponents = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        repository = mock(com.wordpress.lonelytripblog.data.Food2ForkRepository::class.java)
        fromRepo = MutableLiveData()
        `when`(repository.getRecipes()).thenReturn(fromRepo)

        listOfRecipesObserver = mock(Observer::class.java) as Observer<in List<Recipe>?>
        messageObserver = mock(Observer::class.java) as Observer<in String?>

        viewModel = Food2ForkViewModel(repository)

        viewModel.getListOfRecipes().observeForever(listOfRecipesObserver)
        viewModel.getMessageToDisplay().observeForever(messageObserver)
    }

    @Test
    fun onFailureMessageDisplayed() {
        val errorMessage = "Something terrible happened here"
        fromRepo.value = Result.Error(errorMessage)

        verify(messageObserver).onChanged(errorMessage)
    }

    @Test
    fun onSuccessListDisplayed() {
        val listOfRecipes = listOf(
            Recipe("First Title", "imageUrl"),
            Recipe("Second Title", "ImageUrlForSecond")
        )
        fromRepo.value = Result.Success(listOfRecipes)

        verify(listOfRecipesObserver).onChanged(listOfRecipes)
    }
}