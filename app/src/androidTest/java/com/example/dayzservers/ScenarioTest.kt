package com.example.dayzservers

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScenarioTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun firstOpenFailedAndThenSuccess(){
        val mainScreen = MainScreen()
        mainScreen.checkVisible()

        val searchScreen = SearchScreen()
        searchScreen.checkVisible()
        searchScreen.checkError(message = "Service unavailable")
        searchScreen.clickTryAgain()

        searchScreen.checkInfo(servers = 10, players = 228)
        for (i in 0 until 10){
            searchScreen.checkServer(position = i, serverName = "Server #$i")
        }
    }

    @Test
    fun addToFavoriteTwiceAndCheckInFavorites() {
        firstOpenFailedAndThenSuccess()
        val mainScreen = MainScreen()
        val searchScreen = SearchScreen()

        searchScreen.changeFavoriteState(position = 3)
        searchScreen.checkFavorite(position = 3)

        mainScreen.goToFavoritesScreen()
        searchScreen.checkNotVisible()

        val favoritesScreen = FavoritesScreen()
        favoritesScreen.checkVisible()
        favoritesScreen.checkServer(position = 0, serverName = "Server #3")

        mainScreen.goToSearchScreen()
        favoritesScreen.checkNotVisible()
        searchScreen.checkVisible()

        searchScreen.checkInfo(servers = 10, players = 228)
        for (i in 0 until 10){
            searchScreen.checkServer(position = i, serverName = "Server #$i")
        }
        searchScreen.checkFavorite(position = 3)

        searchScreen.changeFavoriteState(position = 1)
        searchScreen.checkFavorite(position = 1)

        mainScreen.goToFavoritesScreen()
        searchScreen.checkNotVisible()
        favoritesScreen.checkVisible()

        favoritesScreen.checkServer(position = 0, serverName = "Server #3")
        favoritesScreen.checkServer(position = 1, serverName = "Server #1")
    }
}