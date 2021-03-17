package com.raywenderlich.chuckyfacts.data.remote.service

import com.raywenderlich.chuckyfacts.BuildConfig
import com.raywenderlich.chuckyfacts.data.remote.model.announcement.AnnouncementRequest
import com.raywenderlich.chuckyfacts.data.remote.model.announcement.AnnouncementResponse
import com.raywenderlich.chuckyfacts.data.remote.model.login.ChallengeSaltRequest
import com.raywenderlich.chuckyfacts.data.remote.model.login.ChallengeSaltResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST

interface AnnouncementService {
    @POST(BuildConfig.API_VERSION + "/announcements")
    suspend fun getAnnouncementAsync(@Body announcementRequest: AnnouncementRequest): AnnouncementResponse
}