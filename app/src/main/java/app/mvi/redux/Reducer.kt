package app.mvi.redux

interface Reducer<S: State, A: Action> {

    /**
     * Given a [currentState] and some [action] the user took, produce  a new state
     */
    fun reduce(currentState: S, action: A): S
}