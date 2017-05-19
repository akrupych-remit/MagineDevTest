package se.remit.akrupych.magindevtestapp.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import se.remit.akrupych.magindevtestapp.Constants
import se.remit.akrupych.magindevtestapp.model.Video
import java.lang.reflect.Type

class VideoDeserializer : JsonDeserializer<Video> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Video {
        val video = Gson().fromJson(json, Video::class.java)
        video.imageLarge = Constants.BASE_URL + video.imageLarge
        video.imageMedium = Constants.BASE_URL + video.imageMedium
        video.imageThumb = Constants.BASE_URL + video.imageThumb
        return video
    }

}