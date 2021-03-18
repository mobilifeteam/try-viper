/*
 * Copyright (c) 2018 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mobilife.employyim.view.activities

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
