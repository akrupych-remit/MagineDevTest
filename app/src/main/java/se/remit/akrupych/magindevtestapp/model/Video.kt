package se.remit.akrupych.magindevtestapp.model

import com.google.gson.annotations.SerializedName

data class Video(
        var title: String?,
        var subtitle: String?,
        var studio: String?,
        var sources: List<String>,
        @SerializedName("image-780x1200") var imageLarge: String?,
        @SerializedName("image-480x270") var imageMedium: String?,
        @SerializedName("thumb") var imageThumb: String?
)