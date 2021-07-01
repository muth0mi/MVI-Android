package app.mvi.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A state container for a given screen
 */
class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    fun dispatch(action: A) {
        val currentState = _state.value
        val newState = reducer.reduce(currentState, action)
        _state.value = newState
    }
}