package edu.msudenver.dailydish.Models

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - RecipeById holds data class for Search Recipe by ID response
 */

data class RecipeById (
    var vegetarian: Boolean,
    var vegan: Boolean,
    var glutenFree: Boolean,
    var dairyFree: Boolean,
    var veryHealthy: Boolean,
    var cheap: Boolean,
    var veryPopular: Boolean,
    var sustainable: Boolean,
    var lowFodmap: Boolean,
    var weightWatcherSmartPoints: Int,
    var gaps: String?,
    var preparationMinutes: Int,
    var cookingMinutes: Int,
    var aggregateLikes: Int,
    var healthScore: Int,
    var creditsText: String?,
    var license: String?,
    var sourceName: String?,
    var pricePerServing: Double,
    var extendedIngredients: ArrayList<ExtendedIngredient>?,
    var id: Int,
    var title: String?,
    var readyInMinutes: Int,
    var servings: Int,
    var sourceUrl: String?,
    var image: String?,
    var imageType: String?,
    var summary: String?,
    var cuisines: ArrayList<Any>?,
    var dishTypes: ArrayList<String>?,
    var diets: ArrayList<Any>?,
    var occasions: ArrayList<Any>?,
    var winePairing: WinePairing?,
    var instructions: String?,
    var analyzedInstructions: ArrayList<Any>?,
    var originalId: Any?,
    var spoonacularSourceUrl: String?,
)

data class ExtendedIngredient (
    var id: Int,
    var aisle: String?,
    var image: String?,
    var consistency: String?,
    var name: String?,
    var nameClean: String?,
    var original: String?,
    var originalName: String?,
    var amount: Double,
    var unit: String?,
    var meta: ArrayList<String>?,
    var measures: Measures?
)

data class Measures (
    var us: Us?,
    var metric: Metric?
)

data class Metric (
    var amount: Double,
    var unitShort: String?,
    var unitLong: String?
)


data class Us (
    var amount: Double,
    var unitShort: String?,
    var unitLong: String?,
)

data class WinePairing (
    var pairedWines: ArrayList<Any>?,
    var pairingText: String?,
    var productMatches: ArrayList<Any>?,
)
