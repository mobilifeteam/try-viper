package com.mobilife.employyim.presenter

import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.DetailContract
import com.mobilife.employyim.entity.Joke
import ru.terrakok.cicerone.Router


class DetailPresenter(override var view: DetailContract.View) : DetailContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun backButtonClicked() {
        router?.exit()
    }

    override fun onViewCreated(joke: Joke) {
        view.showJokeData(joke.id.toString(), joke.text)
    }
}