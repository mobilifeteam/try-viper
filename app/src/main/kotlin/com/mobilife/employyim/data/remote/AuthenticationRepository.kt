package com.mobilife.employyim.data.remote

import com.mobilife.employyim.entity.Salt

interface AuthenticationRepository {
    suspend fun challengeSalt(): Result<Salt>
}