package se.remit.akrupych.magindevtestapp.dagger

import dagger.Component
import se.remit.akrupych.magindevtestapp.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApiModule::class))
public interface ApiComponent {

    fun inject(mainActivity: MainActivity)

}