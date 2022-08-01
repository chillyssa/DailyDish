package edu.msudenver.dailydish
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
import retrofit2.http.Header
import retrofit2.http.Query

interface SpoonacularAPI {
    //GET call to Spoonacular Api to search by ingredients
    @GET("findByIngredients?")
    fun findByIngredients(
        @Query("apiKey") apiKey: String,
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int,
        @Query("ranking") ranking: Int
    ): Call<Array<Response>> // the call must be an Array of Response objects as that is how the results are returned from the api

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