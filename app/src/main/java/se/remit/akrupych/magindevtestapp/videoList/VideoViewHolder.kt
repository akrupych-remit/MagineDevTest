package se.remit.akrupych.magindevtestapp.videoList

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import se.remit.akrupych.magindevtestapp.R

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val image = view.findViewById(R.id.videoImage) as ImageView
    val title = view.findViewById(R.id.videoTitle) as TextView
    val studio = view.findViewById(R.id.videoStudio) as TextView
}