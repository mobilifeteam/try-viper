package com.mobilife.employyim.presenter

import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.LoginContract
import com.mobilife.employyim.contract.SplashContract
import com.mobilife.employyim.contract.WelcomeContract
import com.mobilife.employyim.view.login.LoginActivity
import com.mobilife.employyim.view.login.WelcomeActivity
import com.mobilife.employyim.view.mainpage.MainActivity
import ru.terrakok.cicerone.Router


class WelcomePresenter(override var view: WelcomeContract.View) : WelcomeContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onClickStart() {
        router?.navigateTo(LoginActivity.TAG)
        view.finishView()
    }
}