package com.raywenderlich.chuckyfacts.data.remote

import com.raywenderlich.chuckyfacts.entity.Salt

interface AuthenticationRepository {
    suspend fun challengeSalt(): Result<Salt>
}