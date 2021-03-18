package com.mobilife.employyim.contract

import com.github.kittinunf.fuel.android.core.Json
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.result.Result
import com.mobilife.employyim.data.remote.AnnouncementRepository
import com.mobilife.employyim.data.remote.AuthenticationRepository
import com.mobilife.employyim.data.remote.ErrorHandler
import com.mobilife.employyim.entity.Announcement
import com.mobilife.employyim.entity.Joke
import com.mobilife.employyim.entity.Salt

interface MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishDataList(data: List<Joke>)
        fun showInfoMessage(msg: String)
        fun showSalt(salt: String)
        fun showAnnouncement(content: String)
    }

    interface Presenter {
        val view: View
        val interactor: Interactor
        var errorHandler: ErrorHandler

        // User actions
        fun listItemClicked(joke: Joke?)
        fun getSalt()
        fun getAnnouncement()

        // Model updates
        fun onViewCreated()

        fun onDestroy()
    }

    interface Interactor {
        val authenticationRepository: AuthenticationRepository
        val announcementRepository: AnnouncementRepository

        fun loadJokesList(interactorOutput: (result: Result<Json, FuelError>) -> Unit)
        suspend fun getSalt(): com.mobilife.employyim.data.remote.Result<Salt>
        suspend fun getAnnouncement(): com.mobilife.employyim.data.remote.Result<Announcement?>
    }

    interface InteractorOutput {
        fun onQuerySuccess(data: List<Joke>)
        fun onQueryError()
    }
}
