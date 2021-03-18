package com.mobilife.employyim.view.splashscreen

import android.content.Intent
import android.util.Log
import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.contract.SplashContract
import com.mobilife.employyim.view.login.LoginActivity
import com.mobilife.employyim.view.mainpage.MainActivity
import org.koin.androidx.scope.ScopeActivity
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class SplashActivity : ScopeActivity(), SplashContract.View {
    private val presenter: SplashContract.Presenter by inject { parametersOf(this) }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    MainActivity.TAG -> startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
        presenter.onViewCreated()
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun finishView() {
        // close splash activity
        finish()
    }
}
