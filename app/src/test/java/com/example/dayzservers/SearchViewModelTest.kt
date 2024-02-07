package com.example.dayzservers

import com.example.dayzservers.search.domain.DayzServerDomain
import com.example.dayzservers.search.presentation.SearchViewModel
import com.example.dayzservers.search.presentation.UiState
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {

    private lateinit var viewModel: SearchViewModelTest
    private lateinit var observable: FakeUiObservable<UiState>
    private lateinit var interactor: FakeInteractor
    private lateinit var runAsync: FakeRunAsync
    private lateinit var order: Order

    @Before
    fun setUp() {
        order = Order()
        observable = FakeUiObservable<UiState>(UiState.Empty, order)
        interactor = FakeInteractor(order)
        runAsync = FakeRunAsync(order)
        viewModel = SearchViewModel(
            observable = observable,
            repository = repository,
            runAsync = runAsync
        )
    }

    @Test
    fun errorThanSuccessLoading() {
        repository.returnSuccess = false

        viewModel.init()
        observable.checkUiState(UiState.LoadingData)
        order.checkOrderThenClear(
            OBSERVABLE_UPDATE,
            RUN_ASYNC_BACKGROUND,
            REPOSITORY_LOAD_TOP_SERVERS
        )

        viewModel.notifyObserved()
        observable.checkUiState(UiState.Empty)
        order.checkOrderThenClear(OBSERVABLE_CLEAR)

        runAsync.pingResult()
        observable.checkUiState(UiState.Error(message = "Service unavailable"))
        order.checkOrderThenClear(RUN_ASYNC_UI, OBSERVABLE_UPDATE)

        viewModel.notifyObserved()
        observable.checkUiState(UiState.Empty)
        order.checkOrderThenClear(OBSERVABLE_CLEAR)

        repository.returnSuccess = true

        viewModel.init()
        observable.checkUiState(UiState.LoadingData)
        order.checkOrderThenClear(
            OBSERVABLE_UPDATE,
            RUN_ASYNC_BACKGROUND,
            REPOSITORY_LOAD_TOP_SERVERS
        )

        viewModel.notifyObserved()
        observable.checkUiState(UiState.Empty)
        order.checkOrderThenClear(OBSERVABLE_CLEAR)

        runAsync.pingResult()
        observable.checkUiState(
            UiState.Success(
                listOfServers = listOf<DayzServerUi>(
                    DayzServerUi(name = "DayzServer 1", isFavorite = false),
                    DayzServerUi(name = "DayzServer 2", isFavorite = false),
                    DayzServerUi(name = "DayzServer 3", isFavorite = false)
                )
            )
        )
        order.checkOrderThenClear(RUN_ASYNC_UI, OBSERVABLE_UPDATE)

        viewModel.notifyObserved()
        observable.checkUiState(UiState.Empty)
        order.checkOrderThenClear(OBSERVABLE_CLEAR)
    }
}

private class FakeInteractor(private val order: Order) : ServersInteractor {

    var returnSuccess = true

    override suspend fun topServers(number: Int): LoadResult {
        order.add(INTERACTOR_TOP_SERVERS)
        return if (returnSuccess) {
            LoadResult.Success(
                listOfServers = listOf<DayzServerDomain>(
                    DayzServerDomain(name = "DayzServer 1", isFavorite = false),
                    DayzServerDomain(name = "DayzServer 2", isFavorite = false),
                    DayzServerDomain(name = "DayzServer 3", isFavorite = false)
                )
            )
        } else {
            LoadResult.Error(message = "Service unavailable")
        }
    }
}

