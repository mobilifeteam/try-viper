package com.raywenderlich.chuckyfacts.data.remote

import com.raywenderlich.chuckyfacts.entity.Announcement

interface AnnouncementRepository {
    suspend fun getAnnouncement(): Result<Announcement?>
}