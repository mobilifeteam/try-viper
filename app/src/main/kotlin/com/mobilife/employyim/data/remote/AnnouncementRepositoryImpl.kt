package com.mobilife.employyim.data.remote

import com.mobilife.employyim.data.remote.model.announcement.AnnouncementRequest
import com.mobilife.employyim.data.remote.service.AnnouncementService
import com.mobilife.employyim.entity.Announcement
import com.mobilife.employyim.utils.DeviceUtils

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