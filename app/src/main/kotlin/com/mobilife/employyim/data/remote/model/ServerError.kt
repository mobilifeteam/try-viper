package com.mobilife.employyim.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ServerError(
        @Json(name = "code") var code: String = "",
        @Json(name = "message") var message: String = ""
)
