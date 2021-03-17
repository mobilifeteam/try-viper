package com.raywenderlich.chuckyfacts.data.remote

import com.raywenderlich.chuckyfacts.data.remote.model.login.ChallengeSaltRequest
import com.raywenderlich.chuckyfacts.data.remote.service.AuthenticationService
import com.raywenderlich.chuckyfacts.entity.Salt

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