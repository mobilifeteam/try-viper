package com.mobilife.employyim.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import androidx.transition.TransitionInflater
import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.R
import com.mobilife.employyim.contract.WelcomeContract
import com.mobilife.employyim.utils.setMarginTop
import kotlinx.android.synthetic.main.fragment_welcome.*
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class WelcomeFragment : ScopeFragment(), WelcomeContract.View {

    private val presenter: WelcomeContract.Presenter by inject { parametersOf(this) }
    private val group: Group? by lazy { group_welcome_layout }
    private val btnStart: ViewGroup? by lazy { btn_start }

    private val navigator: Navigator? by lazy {
        object : Navigator {
            override fun applyCommand(command: Command) {
                if (command is Forward) {
                    forward(command)
                }
            }

            private fun forward(command: Forward) {
                when (command.screenKey) {
                    LoginActivity.TAG -> startActivity(Intent(context, LoginActivity::class.java))
                    else -> Log.e("Cicerone", "Unknown screen: " + command.screenKey)
                }
            }
        }
    }

    companion object {
        fun newInstance() = WelcomeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_welcome, container, false)
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

        btnStart?.setOnClickListener {
            presenter.onClickStart()
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

    override fun finishView() {}
}