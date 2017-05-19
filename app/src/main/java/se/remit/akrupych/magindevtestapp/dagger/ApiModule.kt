package se.remit.akrupych.magindevtestapp.dagger

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.remit.akrupych.magindevtestapp.api.MaginApi
import se.remit.akrupych.magindevtestapp.api.VideoDeserializer
import se.remit.akrupych.magindevtestapp.model.Video
import javax.inject.Singleton

@Module
class ApiModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun provideApi(): MaginApi {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create(
                                GsonBuilder()
                                        .registerTypeAdapter(Video::class.java, VideoDeserializer())
                                        .create()
                        )
                )
                .build()
                .create(MaginApi::class.java)
    }
}