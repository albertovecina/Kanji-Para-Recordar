package com.example.kanjipararecordar.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface ViewState
interface ViewAction
interface ViewIntent

abstract class BaseViewModel<V : ViewState, A : ViewAction, I : ViewIntent>() : ViewModel() {

    protected abstract val initialViewState: V

    private val _viewState: MutableStateFlow<V> by lazy { MutableStateFlow(initialViewState) }
    private val _viewActions: MutableSharedFlow<A> = MutableSharedFlow()

    val viewState: StateFlow<V> by lazy { _viewState }
    val viewActions: SharedFlow<A> by lazy { _viewActions }

    abstract fun  load()

    abstract fun processIntent(intent: I)

    fun getState(): V = viewState.value

    protected fun setState(update: V.() -> V) {
        _viewState.update(update)
    }

    protected fun dispatchAction(action: A) {
        viewModelScope.launch {
            _viewActions.emit(action)
        }
    }
}
