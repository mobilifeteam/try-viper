package com.raywenderlich.chuckyfacts.di

import com.raywenderlich.chuckyfacts.DetailContract
import com.raywenderlich.chuckyfacts.MainContract
import com.raywenderlich.chuckyfacts.SplashContract
import com.raywenderlich.chuckyfacts.presenter.DetailPresenter
import com.raywenderlich.chuckyfacts.presenter.MainPresenter
import com.raywenderlich.chuckyfacts.presenter.SplashPresenter
import com.raywenderlich.chuckyfacts.view.activities.DetailActivity
import com.raywenderlich.chuckyfacts.view.activities.MainActivity
import com.raywenderlich.chuckyfacts.view.activities.SplashActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    scope(named<SplashActivity>()) {
        scoped<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
    }
    scope(named<MainActivity>()) {
        scoped<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view) }
    }
    scope(named<DetailActivity>()) {
        scoped<DetailContract.Presenter> { (view: DetailContract.View) -> DetailPresenter(view) }
    }
}
