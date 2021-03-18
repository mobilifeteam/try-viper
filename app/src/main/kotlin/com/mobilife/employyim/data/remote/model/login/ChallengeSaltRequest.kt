package com.mobilife.employyim.data.remote.model.login

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChallengeSaltRequest(@Json(name = "user_id") val userID: String)