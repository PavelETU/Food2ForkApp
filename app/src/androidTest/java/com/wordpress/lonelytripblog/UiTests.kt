package com.wordpress.lonelytripblog

import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.wordpress.lonelytripblog.data.Recipe
import com.wordpress.lonelytripblog.ui.MainActivity
import com.wordpress.lonelytripblog.viewmodel.Food2ForkViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.stopKoin
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class UiTests {
    private lateinit var mockViewModel: Food2ForkViewModel
    private val listOfRecipes = MutableLiveData<List<Recipe>>()
    private val message = MutableLiveData<String>()

    @Before
    fun setUp() {
        mockViewModel = mock(Food2ForkViewModel::class.java)
        `when`(mockViewModel.getListOfRecipes()).thenReturn(listOfRecipes)
        `when`(mockViewModel.getMessageToDisplay()).thenReturn(message)
        StandAloneContext.loadKoinModules(module {
            viewModel {
                mockViewModel
            }
        })
        ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun initiallyGoTextShouldBeDisplayed() {
        onView(withText("Go")).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun afterGoClickedProgressBarIsDisplayed() {
        onView(withText("Go")).perform(click())

        onView(isAssignableFrom(ProgressBar::class.java)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun messageIsDisplayedProperly() {
        val messageToDisplay = "Check out my cool message on the screen!"
        message.postValue(messageToDisplay)

        onView(withText("Go")).perform(click())

        onView(withText(messageToDisplay)).check(matches(isCompletelyDisplayed()))
    }

    @Test
    fun listOfItemsDisplayedProperly() {
        val listOfRecipesToDisplay = listOf(
            Recipe("First Title", ""),
            Recipe("Second Title", "")
        )
        listOfRecipes.postValue(listOfRecipesToDisplay)

        onView(withText("Go")).perform(click())

        onView(withText("First Title")).check(matches(isCompletelyDisplayed()))
        onView(withText("Second Title")).check(matches(isCompletelyDisplayed()))
    }
}