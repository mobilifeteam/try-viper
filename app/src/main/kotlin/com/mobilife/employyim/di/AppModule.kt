package com.mobilife.employyim.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mobilife.employyim.BuildConfig
import com.mobilife.employyim.contract.DetailContract
import com.mobilife.employyim.contract.MainContract
import com.mobilife.employyim.contract.SplashContract
import com.mobilife.employyim.data.remote.*
import com.mobilife.employyim.data.remote.service.AnnouncementService
import com.mobilife.employyim.data.remote.service.AuthenticationService
import com.mobilife.employyim.interactor.MainInteractor
import com.mobilife.employyim.presenter.DetailPresenter
import com.mobilife.employyim.presenter.MainPresenter
import com.mobilife.employyim.presenter.SplashPresenter
import com.mobilife.employyim.utils.DeviceUtils
import com.mobilife.employyim.view.activities.*
import com.mobilife.employyim.view.detail.DetailActivity
import com.mobilife.employyim.view.detail.DetailFragment
import com.mobilife.employyim.view.mainpage.MainActivity
import com.mobilife.employyim.view.mainpage.MainFragment
import com.mobilife.employyim.view.splashscreen.SplashActivity
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


    /* Activity & Fragment */
    scope(named<SplashActivity>()) {
        scoped<SplashContract.Presenter> { (view: SplashContract.View) -> SplashPresenter(view) }
    }
    scope(named<MainActivity>()) {
        scope(named<MainFragment>()) {
            scoped<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get()) }
            scoped<MainContract.Interactor> { MainInteractor(get(), get()) }
        }
    }
    scope(named<DetailActivity>()) {
        scope(named<DetailFragment>()) {
            scoped<DetailContract.Presenter> { (view: DetailContract.View) -> DetailPresenter(view) }
        }
    }
}
