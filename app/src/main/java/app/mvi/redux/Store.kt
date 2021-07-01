package app.mvi.redux

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * A state container for a given screen
 */
class Store<S : State, A : Action>(
    initialState: S,
    private val reducer: Reducer<S, A>,
    private val middlewares: List<Middleware<S, A>> = emptyList()
) {

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    fun dispatch(action: A) {
        middlewares.forEach { middleware -> middleware.process(action, _state.value, this) }

        val newState = reducer.reduce(_state.value, action)
        _state.value = newState
    }
}