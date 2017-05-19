package se.remit.akrupych.magindevtestapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import se.remit.akrupych.magindevtestapp.model.CategoriesResponse

interface MaginApi {

    @GET("videos-enhanced-c.json")
    fun getCategories(): Observable<CategoriesResponse>

}