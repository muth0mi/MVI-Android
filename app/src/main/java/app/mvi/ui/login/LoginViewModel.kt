package app.mvi.ui.login

import androidx.lifecycle.ViewModel
import app.mvi.redux.Store
import app.mvi.ui.login.mvi.LoginAction
import app.mvi.ui.login.mvi.LoginReducer
import app.mvi.ui.login.mvi.LoginViewState
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    private val store = Store(
        initialState = LoginViewState(),
        reducer = LoginReducer()
    )

    val viewState: StateFlow<LoginViewState> = store.state

    fun emailChanged(email: String) {
        val action = LoginAction.EmailChanged(email)
        store.dispatch(action)
    }

    fun passwordChanged(password: String) {
        val action = LoginAction.PasswordChanged(password)
        store.dispatch(action)
    }

    fun loginButtonClicked() {
        val action = LoginAction.SignInButtonClicked
        store.dispatch(action)
    }
}