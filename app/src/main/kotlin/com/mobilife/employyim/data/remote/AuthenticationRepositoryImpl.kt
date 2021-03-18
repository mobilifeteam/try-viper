package com.mobilife.employyim.data.remote

import com.mobilife.employyim.data.remote.model.login.ChallengeSaltRequest
import com.mobilife.employyim.data.remote.service.AuthenticationService
import com.mobilife.employyim.entity.Salt

class AuthenticationRepositoryImpl(
        private val authenticationService: AuthenticationService
) : AuthenticationRepository {
    override suspend fun challengeSalt(): Result<Salt> {
        return try {
            val response = authenticationService.challengeSaltAsync(
                    ChallengeSaltRequest("22"))
            val salt = response.mapToSalt()
            Result.Success(salt)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}