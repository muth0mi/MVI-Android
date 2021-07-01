package app.mvi.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import app.mvi.R
import app.mvi.databinding.ActivityLoginBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity(R.layout.activity_login) {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        /**
         * Subscribe to changes in viewModel's stateflow
         */
        lifecycleScope.launch {
            loginViewModel.viewState.collectLatest { processViewState(it) }
        }


        /**
         * whenever a UI action occurs, proxy that to the viewModel
         */
        binding.email.doOnTextChanged { text, _, _, _ ->
            loginViewModel.emailChanged(text?.toString().orEmpty())
        }

        binding.password.doOnTextChanged { text, _, _, _ ->
            loginViewModel.passwordChanged(text?.toString().orEmpty())
        }

        binding.login.setOnClickListener { loginViewModel.loginButtonClicked() }
    }

    private fun processViewState(viewState: LoginViewState) {
//        binding.email.setText(viewState.email)
//        binding.password.setText(viewState.password)
        binding.loading.visibility = if (viewState.showProgressBar) View.VISIBLE else View.GONE
        binding.email.error = viewState.emailError
        binding.password.error = viewState.passwordError
    }
}