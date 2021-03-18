package com.mobilife.employyim.presenter

import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.MainContract
import com.mobilife.employyim.data.remote.ErrorHandler
import com.mobilife.employyim.entity.Joke
import com.mobilife.employyim.view.detail.DetailActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.terrakok.cicerone.Router
import kotlin.coroutines.CoroutineContext


class MainPresenter(override var view: MainContract.View,
                    override val interactor: MainContract.Interactor,
                    override var errorHandler: ErrorHandler)
    : MainContract.Presenter,
        MainContract.InteractorOutput,
        CoroutineScope {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun listItemClicked(joke: Joke?) {
        router?.navigateTo(DetailActivity.TAG, joke)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var job: Job? = null

    override fun getSalt() {
        job = launch {
            getSaltAsync()
        }
    }

    override fun getAnnouncement() {
        job = launch {
            view.showLoading()
            when (val result = interactor.getAnnouncement()) {
                is com.mobilife.employyim.data.remote.Result.Success -> result.data?.message?.let { view.showAnnouncement(it) }
                is com.mobilife.employyim.data.remote.Result.Error -> result.throwable.let {
                    val error = errorHandler.extract(it)
                    view.showInfoMessage("${error.message} (${error.code})")
                }
            }
            view.hideLoading()
        }
    }

    private suspend fun getSaltAsync() {
        view.showLoading()
        when (val result = interactor.getSalt()) {
            is com.mobilife.employyim.data.remote.Result.Success -> {
                view.showSalt(result.data.challengeToken)
                getAnnouncement()
            }
            is com.mobilife.employyim.data.remote.Result.Error -> result.throwable.let {
                val error = errorHandler.extract(it)
                view.showInfoMessage("${error.message} (${error.code})")
            }
        }
        view.hideLoading()
    }

    override fun onViewCreated() {
        view.showLoading()
        interactor.loadJokesList { result ->
            when (result) {
                is Result.Failure -> {
                    this.onQueryError()
                }
                is Result.Success -> {
                    val jokesJsonObject = result.get().obj()

                    val type = object : TypeToken<List<Joke>>() {}.type
                    val jokesList: List<Joke> =
                            Gson().fromJson(jokesJsonObject.getJSONArray("value").toString(), type)

                    this.onQuerySuccess(jokesList)
                }
            }
        }
    }

    override fun onQuerySuccess(data: List<Joke>) {
        view.hideLoading()
        view.publishDataList(data)
    }

    override fun onQueryError() {
        view.hideLoading()
        view.showInfoMessage("Error when loading data")
    }

    override fun onDestroy() {}
}
