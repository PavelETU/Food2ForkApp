package com.wordpress.lonelytripblog

import android.widget.ProgressBar
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test

class NavigationTests {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
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
}