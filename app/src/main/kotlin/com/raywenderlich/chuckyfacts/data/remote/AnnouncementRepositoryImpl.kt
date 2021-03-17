package com.raywenderlich.chuckyfacts.data.remote

import com.raywenderlich.chuckyfacts.data.remote.model.announcement.AnnouncementRequest
import com.raywenderlich.chuckyfacts.data.remote.service.AnnouncementService
import com.raywenderlich.chuckyfacts.entity.Announcement
import com.raywenderlich.chuckyfacts.utils.DeviceUtils

class AnnouncementRepositoryImpl(
        private val announcementService: AnnouncementService
) : AnnouncementRepository {
    
    override suspend fun getAnnouncement(): Result<Announcement?> {
        return try {
            val response = announcementService.getAnnouncementAsync(
                    AnnouncementRequest(DeviceUtils.manufacturerSerialNumber)
            )
            val announcement = response.mapToAnnouncement()
            Result.Success(announcement)
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}