package se.remit.akrupych.magindevtestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import se.remit.akrupych.magindevtestapp.api.MaginApi
import se.remit.akrupych.magindevtestapp.model.CategoriesResponse
import se.remit.akrupych.magindevtestapp.model.Video
import se.remit.akrupych.magindevtestapp.videoList.VideosAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var maginApi: MaginApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).apiComponent.inject(this)
        videosList.layoutManager = LinearLayoutManager(this)
        requestVideos()
    }

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

    private fun showVideos(response: CategoriesResponse) {
        val allVideos: List<Video> = response.categories.flatMap { it.videos }
        videosList.adapter = VideosAdapter(allVideos, this)
    }
}
