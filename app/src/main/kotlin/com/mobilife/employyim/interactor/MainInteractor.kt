package com.mobilife.employyim.interactor

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result
import com.mobilife.employyim.contract.MainContract
import com.mobilife.employyim.data.remote.AnnouncementRepository
import com.mobilife.employyim.data.remote.AuthenticationRepository
import com.mobilife.employyim.entity.Announcement
import com.mobilife.employyim.entity.Salt


class MainInteractor(override val authenticationRepository: AuthenticationRepository,
                     override val announcementRepository: AnnouncementRepository)
    : MainContract.Interactor {

    companion object {
        const val icndbUrl = "https://api.icndb.com/jokes"
    }

    override fun loadJokesList(interactorOutput: (result: Result<Json, FuelError>) -> Unit) {
        icndbUrl.httpPost().responseJson { _, _, result ->
            interactorOutput(result)
        }
    }

    override suspend fun getSalt(): com.mobilife.employyim.data.remote.Result<Salt> {
        return authenticationRepository.challengeSalt()
    }

    override suspend fun getAnnouncement(): com.mobilife.employyim.data.remote.Result<Announcement?> {
        return announcementRepository.getAnnouncement()
    }


}