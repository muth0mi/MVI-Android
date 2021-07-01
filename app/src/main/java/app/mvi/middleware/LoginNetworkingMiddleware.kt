package app.mvi.middleware

import app.mvi.redux.Middleware
import app.mvi.redux.Store
import app.mvi.repository.LoginRepository
import app.mvi.ui.login.LoginAction
import app.mvi.ui.login.LoginViewState

class LoginNetworkingMiddleware(
    private val loginRepository: LoginRepository
) : Middleware<LoginViewState, LoginAction> {

    override suspend fun process(
        action: LoginAction,
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ) {
        when (action) {
            is LoginAction.SignInButtonClicked -> {
                val validEmail = isEmailValid(currentState, store)
                val validPassword = isPasswordValid(currentState, store)
                if (validEmail && validPassword) {
                    loginUser(store, currentState)
                }
            }
            else -> Unit
        }
    }

    private suspend fun isEmailValid(
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ): Boolean {
        return when {
            currentState.email.isBlank() -> {
                store.dispatch(LoginAction.InvalidEmailSubmitted("Please enter your email"))
                false
            }
            else -> true
        }
    }

    private suspend fun isPasswordValid(
        currentState: LoginViewState,
        store: Store<LoginViewState, LoginAction>
    ): Boolean {
        return when {
            currentState.password.isBlank() -> {
                store.dispatch(LoginAction.InvalidPasswordSubmitted("Please enter a password"))
                false
            }
            else -> true
        }
    }

    private suspend fun loginUser(
        store: Store<LoginViewState, LoginAction>,
        currentState: LoginViewState
    ) {
        store.dispatch(LoginAction.LoginStarted)
        val loggedIn = loginRepository.login(currentState.email, currentState.password)
        if (loggedIn) store.dispatch(LoginAction.LoginCompleted)
        else store.dispatch(LoginAction.LoginFailed(Throwable("Unknown Error Occurred")))
    }
}