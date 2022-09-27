package com.example.baubapchallenge.presentation.login

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.baubapchallenge.R
import com.example.baubapchallenge.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@HiltAndroidTest
class FragmentLoginTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        hiltRule.inject()
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun login_with_ivalid_email() {
        onView(withId(R.id.edit_text_email)).perform(typeText("prueba"), closeSoftKeyboard())
        onView(withId(R.id.edit_text_password)).perform(typeText("1234"), closeSoftKeyboard())

        onView(withId(R.id.button_login)).check(matches(isNotEnabled()))
        onView(withText(R.string.email_error)).check(matches(isDisplayed()))

    }

    @Test
    fun login_with_invalid_password() {
        onView(withId(R.id.edit_text_email)).perform(
            typeText("prueba@gmail.com"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.edit_text_password)).perform(typeText(""))
            .perform(clearText(), closeSoftKeyboard())

        onView(withId(R.id.button_login)).check(matches(isNotEnabled()))
        onView(withText(R.string.password_error)).check(matches(isDisplayed()))
    }

    @Test
    fun login_with_valid_credentials() {
        onView(withId(R.id.edit_text_email)).perform(
            typeText("dulcoral20@gmail.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.edit_text_password)).perform(typeText("prueba"), closeSoftKeyboard())
        onView(withId(R.id.button_login)).check(matches(isEnabled()))
    }

}