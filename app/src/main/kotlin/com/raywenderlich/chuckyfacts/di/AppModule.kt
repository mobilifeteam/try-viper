package com.raywenderlich.chuckyfacts.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
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
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {
    /* Utils */
    single { DeviceUtils }

    /* Network */
    factory {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        moshi
    }

    single {
        val converterFactory: Converter.Factory = MoshiConverterFactory.create(get())
        converterFactory
    }

    single {
        val callAdapterFactory: CallAdapter.Factory = CoroutineCallAdapterFactory()
        callAdapterFactory
    }

    factory { ServiceFactory.create(get(), get(), AuthenticationService::class.java, BuildConfig.END_POINT) }
    factory { ServiceFactory.create(get(), get(), AnnouncementService::class.java, BuildConfig.END_POINT) }

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
