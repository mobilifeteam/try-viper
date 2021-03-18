package com.mobilife.employyim.presenter

import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.LoginContract
import com.mobilife.employyim.contract.SplashContract
import com.mobilife.employyim.view.mainpage.MainActivity
import ru.terrakok.cicerone.Router


class LoginPresenter(override var view: LoginContract.View) : LoginContract.Presenter {

    private val router: Router? by lazy { BaseApplication.INSTANCE.cicerone.router }

    override fun onViewCreated() {
    }
}