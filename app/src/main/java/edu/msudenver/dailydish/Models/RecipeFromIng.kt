package edu.msudenver.dailydish.Models
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - RecipeFromIng holds data class for Search By Ingredient response
 */

data class RecipeFromIng(
    var id: Int,
    var image: String,
    var imageType: String,
    var likes: Int,
    var missedIngredientCount: Int,
    var missedIngredients: Array<IngredientInfo>,
    var title: String,
    var unusedIngredients: Array<String>,
    var usedIngredientCount: Int,
    var usedIngredients: Array<IngredientInfo>

)

data class IngredientInfo(
    var aisle: String,
    var amount: Double,
    var id: Int,
    var image: String,
    var meta: Array<String>,
    var name: String,
    var original: String,
    var originalName: String,
    var unit: String,
    var unitLong: String,
    var unitShort: String
)