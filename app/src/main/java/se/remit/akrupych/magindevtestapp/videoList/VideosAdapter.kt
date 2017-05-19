package se.remit.akrupych.magindevtestapp.videoList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import se.remit.akrupych.magindevtestapp.R
import se.remit.akrupych.magindevtestapp.model.Video

class VideosAdapter(val items: List<Video>, val context: Context) : RecyclerView.Adapter<VideoViewHolder>() {

    override fun getItemCount() = items.count()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.studio.text = item.studio
    }
}