package edu.msudenver.dailydish.Models
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - SpoonacularAPI - Interface for api calls
 */

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularAPI {
    //GET call to Spoonacular Api to search by ingredients
    @GET("findByIngredients?")
    fun findByIngredients(
        @Query("apiKey") apiKey: String,
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int,
        @Query("ranking") ranking: Int
    ): Call<Array<RecipeFromIng>> // the call must be an Array of Response objects as that is how the results are returned from the api

    //GET call to Spoonacular API to look up a recipe by ID
    @GET("{id}/information?")
    fun recipeByID(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrition: Boolean = false,
    ): Call<RecipeById>

    companion object {
        val BASE_URL = "https://api.spoonacular.com/recipes/"
        fun create(): SpoonacularAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(SpoonacularAPI::class.java)
        }
    }

}