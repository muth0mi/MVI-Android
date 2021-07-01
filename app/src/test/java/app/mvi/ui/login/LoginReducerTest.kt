package app.mvi.ui.login

import org.junit.Test
import com.google.common.truth.Truth.assertThat

class LoginReducerTest {

    @Test
    fun emailChanged_updatesEmail() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.EmailChanged("test@email.com")

        val expectedState = inputState.copy(email = "test@email.com")

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)
        assertThat(newState).isEqualTo(expectedState)
    }

    @Test
    fun loginStarted_showsProgressBar() {
        val inputState = LoginViewState()
        val inputAction = LoginAction.LoginStarted

        val reducer = LoginReducer()
        val newState = reducer.reduce(inputState, inputAction)

        assertThat(newState.showProgressBar).isTrue()
    }
}