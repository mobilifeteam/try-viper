package com.mobilife.employyim.data.remote.service

import com.mobilife.employyim.BuildConfig
import com.mobilife.employyim.data.remote.model.announcement.AnnouncementRequest
import com.mobilife.employyim.data.remote.model.announcement.AnnouncementResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AnnouncementService {
    @POST(BuildConfig.API_VERSION + "/announcements")
    suspend fun getAnnouncementAsync(@Body announcementRequest: AnnouncementRequest): AnnouncementResponse
}