package com.example.dayzservers

import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.CompletableFuture.allOf

class MainScreen {
    fun checkVisible() {
        onView(
            allOf(
                withId(R.id.rootLayout),
                isAssignableFrom(LinearLayout::class.java)
            )
        ).check(matches(isDisplayed()))

        onView(
            allOf(withId(R.id.bottomNavigation)),
            isAssignableFrom(BottomNavigationView::class.java),
            withParent(withId(R.id.rootLayot)),
            withParent(isAssignableFrom(LinearLayout::class.java))
        ).check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.container),
                isAssignableFrom(FrameLayout::class.java),
                withParent(withId(R.id.rootLayot)),
                withParent(isAssignableFrom(LinearLayout::class.java))
            )
        ).check(matches(isDisplayed()))
    }

    fun goToFavoritesScreen() {
        onView(
            allOf(
                withId(R.id.favorite_item),
                withText("Favorites")
            )
        ).perform(click())
    }

    fun goToSearchScreen() {
        onView(
            allOf(
                withId(R.id.search_item),
                withText("Search")
            )
        ).perform(click())
    }
}
