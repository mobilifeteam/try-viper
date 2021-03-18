package com.mobilife.employyim.view.login

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.ViewCompat
import com.mobilife.employyim.BaseApplication
import com.mobilife.employyim.R
import com.mobilife.employyim.contract.LoginContract
import com.mobilife.employyim.entity.Joke
import com.mobilife.employyim.utils.setMarginTop
import com.mobilife.employyim.view.detail.DetailActivity
import com.mobilife.employyim.view.widget.CustomInputView
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.scope.ScopeFragment
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class LoginFragment : ScopeFragment(), LoginContract.View {

    private val presenter: LoginContract.Presenter by inject { parametersOf(this) }
    private val inputUsername: CustomInputView? by lazy { custom_username }
    private val inputPassword: CustomInputView? by lazy { custom_password }
    private val textErrorMsg: TextView? by lazy { tv_error_msg }
    private val btnLogin: ViewGroup? by lazy { btn_login }

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
                    DetailActivity.TAG -> startActivity(Intent(context, DetailActivity::class.java)
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
        textErrorMsg?.visibility = View.GONE
        inputUsername?.setUpView("Username", "", false)
        inputPassword?.setUpView("Password", "", true)

        btnLogin?.setOnClickListener {
            textErrorMsg?.visibility = if(textErrorMsg?.visibility == View.GONE) {
                View.VISIBLE
            } else {
                View.GONE
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