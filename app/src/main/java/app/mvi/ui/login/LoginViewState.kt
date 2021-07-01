package app.mvi.ui.login

import app.mvi.redux.State

/**
 * Implementation of [State] that describes the configuration of the login screen at a given time
 */
data class LoginViewState(
    val email: String = "",
    val password: String = "",
    val showProgressBar: Boolean = false,
    val emailError: String? = null,
    val passwordError: String? = null
) : State
