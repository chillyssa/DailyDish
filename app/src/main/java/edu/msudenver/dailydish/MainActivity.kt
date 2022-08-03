package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Main Activity - Landing Page Activity of our app
 */


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to view buttons
        val ingredientButton = findViewById<Button>(R.id.btnIngredients)
        val recipeButton = findViewById<Button>(R.id.btnRecipes)

        // If the user clicks the ingredients button start the IngredientActivity
        ingredientButton.setOnClickListener {
            val intentIngredients = Intent(this, IngredientsActivity::class.java)
            startActivity(intentIngredients)
        }

        // If the user clicks the get recipes button, start the SelectIngredientsActivity
        recipeButton.setOnClickListener {
            val intentRecipes = Intent(this, SelectIngredientsActivity::class.java)
            startActivity(intentRecipes)
        }

    }
}