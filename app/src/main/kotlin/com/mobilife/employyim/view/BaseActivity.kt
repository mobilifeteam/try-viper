package com.mobilife.employyim.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.mobilife.employyim.R
import org.koin.androidx.scope.ScopeActivity

abstract class BaseActivity : ScopeActivity() {
  abstract fun fragment(): Fragment

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    addFragment(savedInstanceState)
  }

  private fun fragmentTag(): String {
    return fragment()::javaClass.name
  }

  private fun addFragment(savedInstanceState: Bundle?) {
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
              .replace(R.id.container, fragment(), fragmentTag())
              .commitNow()
    }
  }

  override fun onResume() {
    super.onResume()
    this.getToolbarInstance()?.let { this.initView(it) }
  }

  private fun initView(toolbar: Toolbar) {
    // Toolbar setup
    // Replaces the 'ActionBar' with the 'Toolbar'
    setSupportActionBar(toolbar)
  }

  abstract fun getToolbarInstance(): Toolbar?
}
