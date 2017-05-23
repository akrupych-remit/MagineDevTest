package se.remit.akrupych.maginedevtestapp.api

import io.reactivex.Observable
import retrofit2.http.GET
import se.remit.akrupych.maginedevtestapp.model.CategoriesResponse

/**
 * Retrofit service for our API
 */
interface MagineAPI {

    /**
     * Returns all the videos from backend
     */
    @GET("videos-enhanced-c.json")
    fun getCategories(): Observable<CategoriesResponse>

}