package app.mvi.middleware

import android.util.Log
import app.mvi.redux.Action
import app.mvi.redux.Middleware
import app.mvi.redux.State
import app.mvi.redux.Store

/**
 * This [Middleware] is responsible for logging every [Action] that is processed to the Logcat, so
 * that we can use this for debugging.
 */
class LoggingMiddleware<S: State, A: Action> : Middleware<S,A>{

    override suspend fun process(action: A, currentState: S, store: Store<S, A>) {
        Log.v("LoggingMiddleware", "Processing Action $action; currentState $currentState")
    }
}