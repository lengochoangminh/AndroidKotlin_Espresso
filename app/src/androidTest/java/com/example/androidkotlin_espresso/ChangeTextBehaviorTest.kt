package com.example.androidkotlin_espresso

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChangeTextBehaviorTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val STRING_TO_BE_TYPED = "Welcome to Kotlin"

    @Test
    fun changeText_sameActivity() {
        // Type text and then press the button
        Espresso.onView(withId(R.id.updateNewText)).perform(
            ViewActions.typeText(STRING_TO_BE_TYPED),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.changeTextBt)).perform(ViewActions.click())

        // Check that the text was changed.
        Espresso.onView(withId(R.id.textToBeChanged)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    STRING_TO_BE_TYPED
                )
            )
        )
    }

    @Test
    fun changeText_newActivity() {
        // Type text and then press the button.
        Espresso.onView(withId(R.id.updateNewText)).perform(
            ViewActions.typeText(STRING_TO_BE_TYPED),
            ViewActions.closeSoftKeyboard()
        )
        Espresso.onView(withId(R.id.activityChangeTextBtn)).perform(ViewActions.click())

        // This view is in a different Activity, no need to tell Espresso.
        Espresso.onView(withId(R.id.resultText)).check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    STRING_TO_BE_TYPED
                )
            )
        )
    }
}