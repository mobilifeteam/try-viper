package com.mobilife.employyim.view.login

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.R
import com.mobilife.employyim.contract.LoginContract
import com.mobilife.employyim.entity.Joke
import com.mobilife.employyim.view.detail.DetailActivity
import com.mobilife.employyim.view.mainpage.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import com.mobilife.employyim.utils.setMarginTop

class LoginFragment : ScopeFragment(), LoginContract.View {

    private val presenter: LoginContract.Presenter by inject { parametersOf(this) }
    private val group: Group? by lazy { group_layout }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                val data = (command.transitionData as Joke)

                when (command.screenKey) {
                    DetailActivity.TAG -> startActivity(Intent(context, MainActivity::class.java)
                            .putExtra("data", data as Parcelable))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstance()
    }


    private fun initInstance() {
        activity?.let {
            ViewCompat.setOnApplyWindowInsetsListener(it.findViewById(R.id.container)) { _, insets ->
                group?.setMarginTop(insets.systemWindowInsetTop)
                insets.consumeSystemWindowInsets()
            }
        }
    }


    override fun onResume() {
        super.onResume()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        BaseApplication.INSTANCE.cicerone.navigatorHolder.removeNavigator()
    }

    override fun finishView() {

    }
}