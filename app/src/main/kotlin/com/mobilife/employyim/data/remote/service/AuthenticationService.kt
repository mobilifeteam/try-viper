package com.mobilife.employyim.data.remote.service

import com.mobilife.employyim.BuildConfig
import com.mobilife.employyim.data.remote.model.login.ChallengeSaltRequest
import com.mobilife.employyim.data.remote.model.login.ChallengeSaltResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationService {
    @POST(BuildConfig.API_VERSION + "/authentication/challenge")
    suspend fun challengeSaltAsync(@Body challengeSaltRequest: ChallengeSaltRequest): ChallengeSaltResponse
}