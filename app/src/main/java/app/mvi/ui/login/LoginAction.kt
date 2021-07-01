package app.mvi.ui.login

import app.mvi.redux.Action

/**
 * All possible actions that can be triggered from the login screen
 */
sealed class LoginAction : Action {
    data class EmailChanged(val newEmail: String) : LoginAction()
    data class PasswordChanged(val newPassword: String) : LoginAction()
    data class InvalidEmailSubmitted(val errorMessage: String) : LoginAction()
    data class InvalidPasswordSubmitted(val errorMessage: String) : LoginAction()
    object SignInButtonClicked : LoginAction()
    object LoginStarted : LoginAction()
    object LoginCompleted : LoginAction()
    data class LoginFailed(val error: Throwable?) : LoginAction()
}