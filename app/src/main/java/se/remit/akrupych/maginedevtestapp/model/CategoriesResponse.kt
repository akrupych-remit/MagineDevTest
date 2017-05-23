package se.remit.akrupych.maginedevtestapp.model

/**
 * Root response class for [MagineAPI.getCategories()][se.remit.akrupych.maginedevtestapp.api.MagineAPI.getCategories]
 */
data class CategoriesResponse(
        var categories: List<Category>
)