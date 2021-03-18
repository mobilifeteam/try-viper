package com.mobilife.employyim.view.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mobilife.employyim.R
import com.mobilife.employyim.entity.Joke
import com.mobilife.employyim.view.BaseActivity
import kotlinx.android.synthetic.main.toolbar_view_custom_layout.*

class DetailActivity : BaseActivity() {

    companion object {
        const val TAG = "DetailActivity"
    }

    private val toolbar: Toolbar by lazy { toolbar_toolbar_view }

    override fun fragment(): Fragment {
        return DetailFragment.newInstance(intent.getParcelableExtra<Joke>("data"))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    override fun onResume() {
        super.onResume()
        // add back arrow to toolbar
        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                false
            }
            else -> false
        }
    }

    override fun getToolbarInstance(): Toolbar = toolbar
}