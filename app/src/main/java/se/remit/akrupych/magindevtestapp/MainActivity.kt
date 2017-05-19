package se.remit.akrupych.magindevtestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import se.remit.akrupych.magindevtestapp.api.MaginAPI
import se.remit.akrupych.magindevtestapp.api.VideoDeserializer
import se.remit.akrupych.magindevtestapp.model.CategoriesResponse
import se.remit.akrupych.magindevtestapp.model.Video
import se.remit.akrupych.magindevtestapp.videoList.VideosAdapter

/**
 * Main and the only screen of the app
 */
class MainActivity : AppCompatActivity() {

    /**
     * API object for accessing backend. Uses RxJava to get response and custom GSON adapter
     * to adjust video image paths
     */
    private val maginApi = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(
                    GsonConverterFactory.create(
                            GsonBuilder()
                                    .registerTypeAdapter(Video::class.java, VideoDeserializer())
                                    .create()
                    )
            )
            .build()
            .create(MaginAPI::class.java)

    /**
     * Called when the screen is created to get the view and setup everything
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        videosList.layoutManager = LinearLayoutManager(this)
        requestVideos()
    }

    /**
     * Ask backend for all the videos and show them in list
     */
    private fun requestVideos() {
        maginApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingIndicator.visibility = View.VISIBLE }
                .doFinally { loadingIndicator.visibility = View.GONE }
                .subscribe(
                        { response ->
                            showVideos(response)
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }

    /**
     * Show loaded videos data on the screen
     */
    private fun showVideos(response: CategoriesResponse) {
        val allVideos: List<Video> = response.categories.flatMap { it.videos }
        videosList.adapter = VideosAdapter(allVideos, this)
    }
}
