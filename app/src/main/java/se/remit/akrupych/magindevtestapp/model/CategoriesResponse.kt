package se.remit.akrupych.magindevtestapp.model

/**
 * Root response class for [MaginAPI.getCategories()][se.remit.akrupych.magindevtestapp.api.MaginAPI.getCategories]
 */
data class CategoriesResponse(
        var categories: List<Category>
)