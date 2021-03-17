package com.raywenderlich.chuckyfacts.data.remote.model.announcement

import com.raywenderlich.chuckyfacts.entity.Announcement
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AnnouncementResponse(
        @Json(name = "announcement") var announcement: AnnouncementModel? = null
) {
    fun mapToAnnouncement(): Announcement? {
        return announcement?.let {
            Announcement(
                    it.code,
                    it.type,
                    it.title,
                    it.message,
                    it.startTimestamp,
                    it.finishTimestamp
            )
        } ?: run {
            null
        }
    }
}

@JsonClass(generateAdapter = true)
data class AnnouncementModel(
        @Json(name = "code") var code: String,
        @Json(name = "type") var type: String? = null,
        @Json(name = "title") var title: String? = null,
        @Json(name = "message") var message: String,
        @Json(name = "start_timestamp") var startTimestamp: String,
        @Json(name = "finish_timestamp") var finishTimestamp: String
)


