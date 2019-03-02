package io.erie.framework

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*

fun Int.checkIsVisible() {
    onView(withId(this)).check(matches(isDisplayed()))
}

fun Int.checkIsSameText(text: String) {
    onView(withId(this)).check(matches(withText(text)))
}

fun Int.performClickWithContentDescription() {
    onView(withContentDescription(this)).perform(click())
}

fun String.performClickWithContentDescription() {
    onView(withContentDescription(this)).perform(click())
}

fun Int.performClick() {
    onView(withId(this)).perform(click())
}
