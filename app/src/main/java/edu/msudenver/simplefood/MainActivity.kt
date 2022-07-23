package edu.msudenver.simplefood


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val ingredientButton = findViewById<Button>(R.id.btnIngredients)
        val recipeButton = findViewById<Button>(R.id.btnRecipes)

        ingredientButton.setOnClickListener {
            val intentIngredients = Intent(this, IngredientsActivity::class.java)
            startActivity(intentIngredients)
        }


        recipeButton.setOnClickListener{
            val intentRecipes = Intent(this, RecipeActivity::class.java)
            startActivity(intentRecipes)
        }

}
}