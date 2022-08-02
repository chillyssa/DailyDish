package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - RecipeInfo Activity to search a recipe by ID and send a user to the url via implicit intents
 */

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.dailydish.Models.DBHelper
import edu.msudenver.dailydish.Models.Ingredient
import edu.msudenver.dailydish.Models.RecipeById
import kotlinx.android.synthetic.main.recipe_list.view.*
import retrofit2.Call
import retrofit2.Callback

class RecipeInfoActivity : AppCompatActivity(), Callback<RecipeById> {

    // TODO: make API call to RecipeByID to get source URL
    // TODO: Implicit intent - send usr to source url in RecipeByID
    override fun onResponse(
        call: Call<RecipeById>,
        response: retrofit2.Response<RecipeById>
    ) {
        TODO("Not yet implemented")
    }

    override fun onFailure(call: Call<RecipeById>, t: Throwable) {
        TODO("Not yet implemented")
    }
}