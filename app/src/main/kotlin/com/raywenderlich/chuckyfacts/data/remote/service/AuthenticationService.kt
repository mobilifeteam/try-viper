package com.raywenderlich.chuckyfacts.data.remote.service

import com.raywenderlich.chuckyfacts.BuildConfig
import com.raywenderlich.chuckyfacts.data.remote.model.login.ChallengeSaltRequest
import com.raywenderlich.chuckyfacts.data.remote.model.login.ChallengeSaltResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST(BuildConfig.API_VERSION + "/authentication/challenge")
    suspend fun challengeSaltAsync(@Body challengeSaltRequest: ChallengeSaltRequest): ChallengeSaltResponse
}