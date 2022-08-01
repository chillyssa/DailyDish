package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Recipe Activity for api connection/recipe search
 */

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback

class RecipeActivity : AppCompatActivity(), Callback<Array<Response>> {

    lateinit var recyclerView: RecyclerView

    // Create the recipe holder view for the recycler view for all recipe information to display
    private inner class RecipeHolder(view: View): RecyclerView.ViewHolder(view) {
        val recipeName: TextView = view.findViewById(R.id.recipeName)
        val missedIngCt: TextView = view.findViewById(R.id.missedIngredientCount)
        val usedIngCt: TextView =view.findViewById(R.id.usedIngredientCount)
        val ingredients: TextView = view.findViewById(R.id.recipeIngredients)
        val recipeURL: TextView = view.findViewById(R.id.recipeURL) // TODO: update this to a clickable URL
    }

    //RecipeAdapter uses the Response data to set the view to the recipe information
    private inner class RecipeAdapter(var recipe: Array<Response>): RecyclerView.Adapter<RecipeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_list, parent, false)
            return RecipeHolder(view)
        }

        override fun onBindViewHolder(holder: RecipeHolder, position: Int) {

            val recipe = recipe[position]
            for(name in recipe.usedIngredients.iterator()){
                val ingredients = name.name
                holder.ingredients.text = "Ingredients used from your list: $ingredients"
            }
            holder.recipeName.text = "Recipe: ${recipe.title}"
            holder.missedIngCt.text = "No. Missing Ingredients: ${recipe.missedIngredientCount}"
            holder.usedIngCt.text = "No. Used Ingredients: ${recipe.usedIngredientCount}"
            holder.recipeURL.text = "URL: ${recipe.image}" //TODO: this is currently the image url. we need to get the recieps Url in a different call.
        }

        override fun getItemCount(): Int {
            return recipe.size
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        //TODOd: set up recycler
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //TODOd: Create SpoonacularAPI object to make api calls
        val spnAPI = SpoonacularAPI.create()
        val query = getString(R.string.testAPICall)
        val apiKey = BuildConfig.API_KEY

        //TODOd: Call to spn to get recipes by ingredients
        val call = spnAPI.findByIngredients(apiKey, query, 10, 1)
        call.enqueue(this)

        //TODO: Call to spn by recipe id to get url and send user to external url via intents
    }

    //SpoonacularApi response is an array of objects, so the response using retrofit needs to be an array of Response types
    override fun onResponse(call: Call<Array<Response>?>, response: retrofit2.Response<Array<Response>?>) {
        val res = response.body()!!
        //passes the result set into the recipe adapter to display the resulting data in the view elements
        recyclerView.adapter = RecipeAdapter(res)

    }

    // Prints a corresponding error message if an error occurs.
    override fun onFailure(call: Call<Array<Response>?>, t: Throwable) {
        println("ERROR: ${t.message}")
    }
}
