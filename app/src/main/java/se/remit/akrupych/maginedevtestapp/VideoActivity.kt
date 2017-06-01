package se.remit.akrupych.maginedevtestapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_video.*

/**
 * Video playback screen
 */
class VideoActivity : AppCompatActivity() {

    companion object {
        /**
         * Extra key for URL of the video to play
         */
        public val EXTRA_VIDEO_PATH = "EXTRA_VIDEO_PATH"

        /**
         * Instance state key of the current video position
         */
        private val KEY_VIDEO_POSITION = "KEY_VIDEO_POSITION"
    }

    /**
     * Setup video player, restore position and start playback
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        videoView.setVideoPath(intent.getStringExtra(EXTRA_VIDEO_PATH))
        videoView.setOnCompletionListener { finish() }
        videoView.setOnPreparedListener { videoProgressBar.visibility = View.GONE }
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_VIDEO_POSITION)) {
            videoView.seekTo(savedInstanceState.getInt(KEY_VIDEO_POSITION))
        }
        videoView.start()
    }

    /**
     * Save current position
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(KEY_VIDEO_POSITION, videoView.currentPosition)
        super.onSaveInstanceState(outState)
    }
}