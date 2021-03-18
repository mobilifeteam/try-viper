package com.mobilife.employyim.contract

import com.mobilife.employyim.entity.Joke

interface DetailContract {
    interface View {
        fun showJokeData(id: String, joke: String)
        fun showInfoMessage(msg: String)
    }

    interface Presenter {
        val view: View

        // User actions
        fun backButtonClicked()

        // Model updates
        fun onViewCreated(joke: Joke)
    }
}
