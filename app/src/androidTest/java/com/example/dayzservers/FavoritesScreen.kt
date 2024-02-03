package com.example.dayzservers

import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not

class FavoritesScreen {

    private val favoritesLayoutId: Int = R.id.favoritesLayout
    private val favoritesLayout = onView(
        allOf(
            withId(favoritesLayoutId),
            isAssignableFrom(LinearLayout::class.java),
            withParent(withId(R.id.container)),
            withParent(isAssignableFrom(FrameLayout::class.java))
        )
    )
    private val favoritesTitle = onView(
        allOf(
            withText("Favorite servers"),
            withParent(withId(favoritesLayoutId))
        )
    )
    private val favoriteRecyclerViewId = R.id.favoritesRecyclerView
    private val favoritesRecyclerView = onView(
        allOf(
            withId(favoriteRecyclerViewId),
            isAssignableFrom(RecyclerView::class.java),
            withParent(withId(favoritesLayoutId))
        )
    )

    fun checkVisible() {
        favoritesLayout.check(matches(isDisplayed()))
        favoritesTitle.check(matches(isDisplayed()))
        favoritesRecyclerView.check(matches(isDisplayed()))
    }

    fun checkNotVisible() {
        favoritesLayout.check(matches(not( isDisplayed())))
    }

    fun checkServer(position: Int, serverName: String) {
        onView(RecyclerViewMatcher(favoriteRecyclerViewId, position, R.id.serverNameTextView))
            .check(matches(withText(serverName)))
    }
}
