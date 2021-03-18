package com.mobilife.employyim.presenter

import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.SplashContract
import com.mobilife.employyim.view.mainpage.MainActivity
import ru.terrakok.cicerone.Router


class SplashPresenter(override var view: SplashContract.View) : SplashContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onViewCreated() {
        router?.navigateTo(MainActivity.TAG)
        view.finishView()
    }
}