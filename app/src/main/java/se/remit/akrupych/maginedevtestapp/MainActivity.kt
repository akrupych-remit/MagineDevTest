package se.remit.akrupych.maginedevtestapp

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
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
import se.remit.akrupych.maginedevtestapp.api.MagineAPI
import se.remit.akrupych.maginedevtestapp.api.VideoDeserializer
import se.remit.akrupych.maginedevtestapp.model.CategoriesResponse
import se.remit.akrupych.maginedevtestapp.model.Video
import se.remit.akrupych.maginedevtestapp.videoList.VideosAdapter

/**
 * Main and the only screen of the app
 */
class MainActivity : AppCompatActivity() {

    /**
     * API object for accessing backend. Uses RxJava to get response and custom GSON adapter
     * to adjust video image paths
     */
    private val magineApi = Retrofit.Builder()
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
            .create(MagineAPI::class.java)

    private var errorSnackbar: Snackbar? = null

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
        magineApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    errorSnackbar?.dismiss()
                    loadingIndicator.visibility = View.VISIBLE
                }
                .doFinally { loadingIndicator.visibility = View.GONE }
                .subscribe(
                        { response ->
                            showVideos(response)
                        },
                        { error ->
                            error.printStackTrace()
                            errorSnackbar = Snackbar.make(contentView, R.string.loading_failed, Snackbar.LENGTH_INDEFINITE)
                            errorSnackbar?.setAction(R.string.retry, { requestVideos() })
                            errorSnackbar?.show()
                        }
                )
    }

    /**
     * Show loaded videos data on the screen
     */
    private fun showVideos(response: CategoriesResponse) {
        val allVideos: List<Video> = response.categories.flatMap { it.videos }
        val adapter = VideosAdapter(allVideos, this)
        adapter.getItemClicksObservable().subscribe({ playVideo(it) })
        videosList.adapter = adapter
    }

    /**
     * Show video playback screen
     */
    private fun playVideo(video: Video) {
        startActivity(Intent(this, VideoActivity::class.java).putExtra(VideoActivity.EXTRA_VIDEO_PATH, video.sources[0]))
    }

}
