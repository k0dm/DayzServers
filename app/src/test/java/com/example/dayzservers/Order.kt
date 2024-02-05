package com.example.dayzservers

internal class Order {

   private val list = mutableListOf<String>()

   fun add(name: String) = list.add(name)

   fun checkOrderThenClear(vararg names: String): Boolean {
       val theSame = names.toList() == list
       list.clear()
       return theSame
   }
}

internal const val RUN_ASYNC_BACKGROUND = "RunAsync#runAsync{backgroundBlock.invoke()}"
internal const val RUN_ASYNC_UI = "RunAsync#runAsync{uiBlock.invoke()}"
internal const val OBSERVABLE_UPDATE_OBSERVER = "UiObservable#updateUiObserver"
internal const val OBSERVABLE_UPDATE = "UiObservable#updateUi"
internal const val OBSERVABLE_CLEAR = "UiObservable#clear"
internal const val INTERACTOR_TOP_SERVERS = "Interactor#topServers"