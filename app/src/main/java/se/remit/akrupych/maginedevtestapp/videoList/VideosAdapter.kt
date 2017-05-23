package se.remit.akrupych.maginedevtestapp.videoList

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import se.remit.akrupych.maginedevtestapp.R
import se.remit.akrupych.maginedevtestapp.model.Video

/**
 * Adapter for [RecyclerView] with videos
 */
class VideosAdapter(val items: List<Video>, val context: Context) : RecyclerView.Adapter<VideoViewHolder>() {

    /**
     * Returns list items count
     */
    override fun getItemCount() = items.size

    /**
     * Returns new [VideoViewHolder] with new row view
     */
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(view)
    }

    /**
     * Binds view to corresponding data item
     */
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val item = items[position]
        Glide.with(context).load(item.imageThumb).placeholder(R.mipmap.ic_launcher).into(holder.image)
        holder.title.text = item.title
        holder.studio.text = item.studio
    }
}