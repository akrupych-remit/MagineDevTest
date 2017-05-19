package se.remit.akrupych.magindevtestapp

import android.app.Application
import se.remit.akrupych.magindevtestapp.dagger.ApiComponent
import se.remit.akrupych.magindevtestapp.dagger.ApiModule
import se.remit.akrupych.magindevtestapp.dagger.DaggerApiComponent

class App : Application() {

    lateinit var apiComponent: ApiComponent

    override fun onCreate() {
        super.onCreate()
        apiComponent = DaggerApiComponent.builder().apiModule(ApiModule(Constants.BASE_URL)).build()
    }
}