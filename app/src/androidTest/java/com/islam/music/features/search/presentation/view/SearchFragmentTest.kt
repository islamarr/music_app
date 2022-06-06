package com.islam.music.features.search.presentation.view

import android.os.Bundle
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.music.R
import com.islam.music.features.main_screen.presentation.view.MainScreenFragment
import com.islam.music.utils.EspressoIdlingResourceRule
import com.islam.music.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
@MediumTest
@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get: Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

    @Test
    fun check_SearchIcon() {
        launchFragmentInHiltContainer<MainScreenFragment>(Bundle(), R.style.Theme_Music)
        Espresso.onView(withId(R.id.startSearch))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}