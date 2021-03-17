package com.raywenderlich.chuckyfacts.di

import com.raywenderlich.chuckyfacts.BuildConfig
import com.raywenderlich.chuckyfacts.DetailContract
import com.raywenderlich.chuckyfacts.MainContract
import com.raywenderlich.chuckyfacts.SplashContract
import com.raywenderlich.chuckyfacts.data.remote.*
import com.raywenderlich.chuckyfacts.data.remote.service.AnnouncementService
import com.raywenderlich.chuckyfacts.data.remote.service.AuthenticationService
import com.raywenderlich.chuckyfacts.interactor.MainInteractor
import com.raywenderlich.chuckyfacts.presenter.DetailPresenter
import com.raywenderlich.chuckyfacts.presenter.MainPresenter
import com.raywenderlich.chuckyfacts.presenter.SplashPresenter
import com.raywenderlich.chuckyfacts.utils.DeviceUtils
import com.raywenderlich.chuckyfacts.view.activities.DetailActivity
import com.raywenderlich.chuckyfacts.view.activities.MainActivity
import com.raywenderlich.chuckyfacts.view.activities.SplashActivity
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    /* Utils */
    single { DeviceUtils }

    /* Network */
    single { ServiceFactory.create(AuthenticationService::class.java, BuildConfig.END_POINT) }
    single { ServiceFactory.create(AnnouncementService::class.java, BuildConfig.END_POINT) }

    /* Repository */
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
    single<AnnouncementRepository> { AnnouncementRepositoryImpl(get()) }

    /* Activity */
    scope(named<SplashActivity>()) {
        scoped<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
    }
    scope(named<MainActivity>()) {
        scoped<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get()) }
        scoped<MainContract.Interactor> { MainInteractor(get(), get()) }
    }
    scope(named<DetailActivity>()) {
        scoped<DetailContract.Presenter> { (view: DetailContract.View) -> DetailPresenter(view) }
    }
}
