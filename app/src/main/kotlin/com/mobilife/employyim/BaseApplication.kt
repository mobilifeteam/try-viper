package com.mobilife.employyim

import android.app.Application
import com.mobilife.employyim.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

class BaseApplication : Application() {

  companion object {
    lateinit var INSTANCE: BaseApplication
  }

  init {
    INSTANCE = this
  }

  // Routing layer (VIPER)
  lateinit var cicerone: Cicerone<Router>

  override fun onCreate() {
    super.onCreate()
    INSTANCE = this
    startKoin {
      androidLogger(Level.DEBUG)
      androidContext(this@BaseApplication)
      modules(appModule)
    }
    this.initCicerone()
  }

  private fun BaseApplication.initCicerone() {
    this.cicerone = Cicerone.create()
  }
}