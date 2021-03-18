package com.mobilife.employyim.view.mainpage

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mobilife.employyim.R
import com.mobilife.employyim.view.BaseActivity
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class MainActivity : BaseActivity() {

    companion object {
        const val TAG: String = "MainActivity"
    }

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }

    override fun fragment(): Fragment {
        return MainFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getToolbarInstance(): Toolbar = toolbar
}
