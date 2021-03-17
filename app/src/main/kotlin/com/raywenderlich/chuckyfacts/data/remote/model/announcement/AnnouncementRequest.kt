package com.raywenderlich.chuckyfacts.data.remote.model.announcement

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnouncementRequest(@Json(name = "device_id") var deviceId: String)