package com.mobilife.employyim.view.login

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mobilife.employyim.R
import com.mobilife.employyim.view.BaseActivity
import com.mobilife.employyim.utils.makeStatusBarTransparent

class WelcomeActivity : BaseActivity() {

    companion object {
        const val TAG = "WelcomeActivity"
    }

    override fun fragment(): Fragment {
        return WelcomeFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        makeStatusBarTransparent()
    }

    override fun getToolbarInstance(): Toolbar? = null
}