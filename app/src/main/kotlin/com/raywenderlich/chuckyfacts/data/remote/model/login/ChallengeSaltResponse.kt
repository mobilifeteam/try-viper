package com.raywenderlich.chuckyfacts.data.remote.model.login

import com.raywenderlich.chuckyfacts.entity.Salt
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChallengeSaltResponse(
        @Json(name = "challenge_token")
        val challengeToken: String = ""
) {
    fun mapToSalt(): Salt {
        return Salt(challengeToken)
    }
}

