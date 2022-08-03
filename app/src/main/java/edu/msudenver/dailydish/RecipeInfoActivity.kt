package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - RecipeInfo Activity to search a recipe by ID and send a user to the url via implicit intents
 */

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import edu.msudenver.dailydish.Models.RecipeById
import edu.msudenver.dailydish.Models.SpoonacularAPI
import retrofit2.Call
import retrofit2.Callback

class RecipeInfoActivity : AppCompatActivity(), Callback<RecipeById> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make API call to RecipeByID to get source URL
        val spnAPI = SpoonacularAPI.create()
        val apiKey = BuildConfig.API_KEY //reference to hidden API key in local.properties

        // Get the recipeID intent from RecipeActivity
        val recipeID = intent.getIntExtra("recipeID", -1)

        // Call to spn to get recipes by ingredients
        val call = spnAPI.recipeByID(recipeID, apiKey, false)
        call.enqueue(this)
    }

    override fun onResponse(
        call: Call<RecipeById>,
        response: retrofit2.Response<RecipeById>
    ) {
        val res = response.body()!!
        // if response array is not empty, pass the result set into the recipe adapter to display the resulting data in the view elements
        if(res != null){
            // Implicit intent - send usr to source url in RecipeByID
            println("RECIPE NAME: ${res.sourceName}\nURL: ${res.sourceUrl}")
            val url = res.sourceUrl
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } else{
            // Else alert the user that there was no response from the api call
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Your search returned no results :(")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.show()
        }
    }

    // If there is a failure in the api call, alert the user and print the details of the failure
    override fun onFailure(call: Call<RecipeById>, t: Throwable) {
        println("ERROR: ${t.message}")
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("There was an error with your search :(\nPlease Try Again\n${t.message}")
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.show()
    }
}


