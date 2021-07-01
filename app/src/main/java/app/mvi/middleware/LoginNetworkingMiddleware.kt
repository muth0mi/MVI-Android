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
                store.dispatch(LoginAction.LoginStarted)

                val loggedIn = loginRepository.login(currentState.email, currentState.password)
                if (loggedIn) store.dispatch(LoginAction.LoginCompleted)
                else store.dispatch(LoginAction.LoginFailed(Throwable("Unknown Error Occurred")))
            }
            else -> Unit
        }
    }
}