package app.mvi.redux

/**
 * Deals with side-effects of actions
 */
interface Middleware<S: State, A:Action> {

    suspend fun process(action:A, currentState:S, store: Store<S,A>)
}