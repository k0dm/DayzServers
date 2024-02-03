package com.example.dayzservers

import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.not
import java.util.concurrent.CompletableFuture.allOf

class SearchScreen {

    private val searchLayoutId = R.id.searchLayout
    private val searchLayout = onView(
        allOf(
            withId(searchLayoutId),
            isAssignableFrom(LinearLayout::class.java),
            withParent(withId(R.id.container)),
            withParent(isAssignableFrom(FrameLayout::class.java))
        )
    )

    private val gameInfoLayout = onView(
        allOf(
            withId(R.id.gameInfoLayout),
            isAssignableFrom(LinearLayout),
            withParent(withId(searchLayout))
        )
    )
    private val serversTextView = onView(
        allOf(
            withId(R.id.serversTextView),
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(gameInfoLayout))
        )
    )
    private val playersTextView = onView(
        allOf(
            withId(R.id.playersTextView),
            isAssignableFrom(TextView::class.java),
            withParent(isAssignableFrom(gameInfoLayout))
        )
    )

    private val recyclerViewId = R.id.searchRecyclerView
    private val recyclerView = onView(
        allOf(
            withId(recyclerViewId),
            isAssignableFrom(RecyclerView::class.java),
            withParent(withId(searchLayoutId))
        )
    )

    fun checkVisible() {
        searchLayout.check(matches(isDisplayed()))
        gameInfoLayout.check(matches(isDisplayed()))
        recyclerView.check(matches(isDisplayed()))
    }

    fun checkNotVisible() {
        searchLayout.check(matches(not(isDisplayed())))
    }

    fun changeFavoriteState(position: Int) {
        onView(RecyclerViewMatcher(recyclerViewId, position, R.id.favoriteImageView))
            .perform(click())
    }

    fun checkFavorite(position: Int) {
        onView(RecyclerViewMatcher(recyclerViewId, position, R.id.favoriteImageView))
            .check(ImageViewDrawableMatcher(R.drawable.favorite))
    }

    fun checkError(message: String) {
        onView(RecyclerViewMatcher(recyclerViewId, 0, R.id.errorTextView))
            .check(matches(withText(message)))
    }

    fun clickTryAgain() {
        onView(RecyclerViewMatcher(recyclerViewId, 0, R.id.tryAgainButton))
            .perform(click())
    }

    fun checkInfo(servers: Int, players: Int) {
        serversTextView.check(matches(withText(servers.toString())))
        playersTextView.check(matches(withText(players)))
    }

    fun checkServer(position: Int, serverName: String) {
        onView(RecyclerViewMatcher(recyclerViewId, position, R.id.serverNameTextView))
            .check(matches(withText(serverName)))
    }
}
