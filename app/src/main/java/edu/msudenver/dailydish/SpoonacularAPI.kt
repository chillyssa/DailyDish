package edu.msudenver.dailydish

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularAPI {
//    @GET("recipe")
//    fun recipe(@Query("q") q: String, @Query("apiKey") apiKey: String): Call<Response>

    companion object {
        val BASE_URL = "https://api.spoonacular.com/recipes/findByIngredients"
        val API_KEY = ""

        fun create(): SpoonacularAPI {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(SpoonacularAPI::class.java)
        }
    }

}