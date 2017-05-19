package se.remit.akrupych.magindevtestapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import se.remit.akrupych.magindevtestapp.model.CategoriesResponse

/**
 * Retrofit service for our API
 */
interface MaginAPI {

    /**
     * Returns all the videos from backend
     */
    @GET("videos-enhanced-c.json")
    fun getCategories(): Observable<CategoriesResponse>

}