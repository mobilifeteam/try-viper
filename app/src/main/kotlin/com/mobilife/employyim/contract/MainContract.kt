/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
