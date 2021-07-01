package app.mvi.ui.login

import android.util.Log
import app.mvi.redux.Reducer

/**
 * Handle any [LoginAction] and create a new [LoginViewState] accordingly
 */
class LoginReducer : Reducer<LoginViewState, LoginAction> {

    /**
     * Return a new [LoginViewState]
     */
    override fun reduce(currentState: LoginViewState, action: LoginAction): LoginViewState {
        Log.v("LoginReducer", "Processing Action $action")

        return when (action) {
            is LoginAction.EmailChanged -> currentState.copy(email = action.newEmail)
            is LoginAction.PasswordChanged -> currentState.copy(password = action.newPassword)
            LoginAction.SignInButtonClicked -> currentState
            LoginAction.LoginStarted -> currentState.copy(showProgressBar = true)
            LoginAction.LoginCompleted -> currentState.copy(showProgressBar = false)
            is LoginAction.LoginFailed -> currentState.copy(showProgressBar = false)
        }
    }
}