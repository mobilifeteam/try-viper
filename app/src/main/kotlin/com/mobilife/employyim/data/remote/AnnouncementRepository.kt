package com.mobilife.employyim.data.remote

import com.mobilife.employyim.entity.Announcement

interface AnnouncementRepository {
    suspend fun getAnnouncement(): Result<Announcement?>
}