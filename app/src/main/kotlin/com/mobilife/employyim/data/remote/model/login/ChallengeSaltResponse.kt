package com.mobilife.employyim.data.remote.model.login

import com.mobilife.employyim.entity.Salt
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

