package com.raywenderlich.chuckyfacts.di

import com.raywenderlich.chuckyfacts.BuildConfig
import com.raywenderlich.chuckyfacts.DetailContract
import com.raywenderlich.chuckyfacts.MainContract
import com.raywenderlich.chuckyfacts.SplashContract
import com.raywenderlich.chuckyfacts.data.remote.AuthenticationRepository
import com.raywenderlich.chuckyfacts.data.remote.AuthenticationRepositoryImpl
import com.raywenderlich.chuckyfacts.data.remote.ServiceFactory
import com.raywenderlich.chuckyfacts.data.remote.service.AuthenticationService
import com.raywenderlich.chuckyfacts.interactor.MainInteractor
import com.raywenderlich.chuckyfacts.presenter.DetailPresenter
import com.raywenderlich.chuckyfacts.presenter.MainPresenter
import com.raywenderlich.chuckyfacts.presenter.SplashPresenter
import com.raywenderlich.chuckyfacts.view.activities.DetailActivity
import com.raywenderlich.chuckyfacts.view.activities.MainActivity
import com.raywenderlich.chuckyfacts.view.activities.SplashActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    /* Network */
    single { ServiceFactory.createNetworkClient(BuildConfig.END_POINT) }

    /* Repository */
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }

    /* Activity */
    scope(named<SplashActivity>()) {
        scoped<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
    }
    scope(named<MainActivity>()) {
        scoped<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get()) }
        scoped<MainContract.Interactor> { MainInteractor(get()) }
    }
    scope(named<DetailActivity>()) {
        scoped<DetailContract.Presenter> { (view: DetailContract.View) -> DetailPresenter(view) }
    }
}
