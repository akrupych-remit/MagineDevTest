package se.remit.akrupych.maginedevtestapp.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import se.remit.akrupych.maginedevtestapp.Constants
import se.remit.akrupych.maginedevtestapp.model.Video
import java.lang.reflect.Type

/**
 * Custom JSON parser for server path prepending
 */
class VideoDeserializer : JsonDeserializer<Video> {

    /**
     * Called every time GSON is parsing Video object.
     * Add server path here.
     */
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Video {
        val video = Gson().fromJson(json, Video::class.java)
        video.imageLarge = Constants.BASE_URL + video.imageLarge
        video.imageMedium = Constants.BASE_URL + video.imageMedium
        video.imageThumb = Constants.BASE_URL + video.imageThumb
        return video
    }

}