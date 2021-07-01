package app.mvi.repository

import kotlinx.coroutines.delay

class LoginRepository {

    suspend fun login(email: String, password: String): Boolean {
        delay(2500)
        return true
    }
}