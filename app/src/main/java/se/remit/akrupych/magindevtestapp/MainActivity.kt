package se.remit.akrupych.magindevtestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    private val maginApi = Retrofit.Builder()
            .baseUrl("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MaginAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        maginApi.getCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { loadingIndicator.visibility = View.VISIBLE }
                .doFinally { loadingIndicator.visibility = View.GONE }
                .subscribe(
                        { response ->
                            responseTextView.text = response.toString()
                        },
                        { error ->
                            error.printStackTrace()
                        }
                )
    }
}
