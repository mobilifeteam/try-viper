package com.mobilife.employyim.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mobilife.employyim.BuildConfig
import com.mobilife.employyim.contract.*
import com.mobilife.employyim.data.remote.*
import com.mobilife.employyim.data.remote.service.AnnouncementService
import com.mobilife.employyim.data.remote.service.AuthenticationService
import com.mobilife.employyim.interactor.MainInteractor
import com.mobilife.employyim.presenter.*
import com.mobilife.employyim.utils.DeviceUtils
import com.mobilife.employyim.view.detail.DetailActivity
import com.mobilife.employyim.view.detail.DetailFragment
import com.mobilife.employyim.view.login.LoginActivity
import com.mobilife.employyim.view.login.LoginFragment
import com.mobilife.employyim.view.login.WelcomeActivity
import com.mobilife.employyim.view.login.WelcomeFragment
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

    /* Error Handler */
    single { ErrorHandler() }

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
    scope(named<WelcomeActivity>()) {
        scope(named<WelcomeFragment>()) {
            scoped<WelcomeContract.Presenter> { (view: WelcomeContract.View) -> WelcomePresenter(view) }
        }
    }
    scope(named<LoginActivity>()) {
        scope(named<LoginFragment>()) {
            scoped<LoginContract.Presenter> { (view: LoginContract.View) -> LoginPresenter(view) }
        }
    }
    scope(named<MainActivity>()) {
        scope(named<MainFragment>()) {
            scoped<MainContract.Presenter> { (view: MainContract.View) -> MainPresenter(view, get(), get()) }
            scoped<MainContract.Interactor> { MainInteractor(get(), get()) }
        }
    }
    scope(named<DetailActivity>()) {
        scope(named<DetailFragment>()) {
            scoped<DetailContract.Presenter> { (view: DetailContract.View) -> DetailPresenter(view) }
        }
    }
}
