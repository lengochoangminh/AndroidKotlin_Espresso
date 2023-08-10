package com.example.androidkotlin_espresso

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CustomMatchersEditTextTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun customMatcherWithEditText() {
        val hintText =
            ApplicationProvider.getApplicationContext<Context>().resources.getString(R.string.hint)

        onView(withId(R.id.editText)).check(matches(EditTextWithItemHint(hintText)));

        onView(withId(R.id.editText)).perform(
            ViewActions.typeText("Espresso"), ViewActions.closeSoftKeyboard()
        )

        onView(withId(R.id.editText)).check(matches(textViewTextColorMatcher(Color.BLACK)))
    }

    // Additional Espresso Test for this demo application
    @Test
    fun validation_resultIsOneOfTheValidStrings() {
        // Type a valid string and click on the button.
        onView(withId(R.id.editText))
            .perform(ViewActions.typeText("Espresso"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.button)).perform(ViewActions.click())

        // Check that the correct sign is displayed.
        onView(withId(R.id.inputValidationSuccess)).check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.inputValidationError)).check(matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    // Custom matcher - Verify if a text matches the text of an EditText hint
    fun EditTextWithItemHint(matcherText: String): Matcher<View?>? {
        checkNotNull(matcherText)
        return object : BoundedMatcher<View?, EditText>(EditText::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with item hint: $matcherText")
            }

            override fun matchesSafely(editTextField: EditText): Boolean {
                return matcherText.equals(editTextField.hint.toString(), ignoreCase = true)
            }
        }
    }

    // Custom matcher - Verifies that a TextView has an specific color
    fun textViewTextColorMatcher(matcherColor: Int): Matcher<View?>? {
        return object : BoundedMatcher<View?, TextView>(TextView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("with text color: $matcherColor")
            }

            override fun matchesSafely(textView: TextView): Boolean {
                return matcherColor == textView.currentTextColor
            }
        }
    }
}