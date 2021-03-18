package com.mobilife.employyim.view.login

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mobilife.employyim.R
import com.mobilife.employyim.view.BaseActivity
import com.mobilife.employyim.utils.makeStatusBarTransparent

class LoginActivity : BaseActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    override fun fragment(): Fragment {
        return LoginFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        makeStatusBarTransparent()
    }

    override fun onResume() {
        super.onResume()
        // add back arrow to toolbar
        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun getToolbarInstance(): Toolbar? = null
}