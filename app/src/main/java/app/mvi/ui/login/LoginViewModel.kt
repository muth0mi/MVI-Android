package app.mvi.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mvi.middleware.LoggingMiddleware
import app.mvi.middleware.LoginNetworkingMiddleware
import app.mvi.redux.Store
import app.mvi.repository.LoginRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository = LoginRepository()

    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer(),
        middlewares = listOf(
            LoggingMiddleware(),
            LoginNetworkingMiddleware(loginRepository)
        )
    )

    val viewState: StateFlow<LoginViewState> = store.state

    fun emailChanged(email: String) = viewModelScope.launch {
        val action = LoginAction.EmailChanged(email)
        store.dispatch(action)
    }

    fun passwordChanged(password: String) = viewModelScope.launch {
        val action = LoginAction.PasswordChanged(password)
        store.dispatch(action)
    }

    fun loginButtonClicked() = viewModelScope.launch {
        val action = LoginAction.SignInButtonClicked
        store.dispatch(action)
    }
}