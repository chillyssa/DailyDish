package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Recipe Activity for api connection/recipe search
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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.dailydish.Models.DBHelper
import edu.msudenver.dailydish.Models.Ingredient
import edu.msudenver.dailydish.Models.RecipeById
import kotlinx.android.synthetic.main.recipe_list.view.*
import retrofit2.Call
import retrofit2.Callback

class RecipeActivity : AppCompatActivity(), View.OnClickListener, Callback<Array<Response>> {

    lateinit var recyclerView: RecyclerView
    lateinit var dbHelper: DBHelper
    lateinit var db: SQLiteDatabase
    private val ISO_FORMAT = DBHelper.ISO_FORMAT
    val ingredientNames = mutableListOf<String>()
    var recipeID = ""

    // Create the recipe holder view for the recycler view for all recipe information to display
    inner class RecipeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeName: TextView = view.findViewById(R.id.recipeName)
        val missedIngCt: TextView = view.findViewById(R.id.missedIngredientCount)
        val usedIngCt: TextView = view.findViewById(R.id.usedIngredientCount)
        val ingredients: TextView = view.findViewById(R.id.recipeIngredients)
        val recipeID: TextView = view.findViewById(R.id.recipeID)
    }

    //RecipeAdapter uses the Response data to set the view to the recipe information
    inner class RecipeAdapter(var recipeByIng: Array<Response>, var onClickListener: View.OnClickListener) :
        RecyclerView.Adapter<RecipeHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.recipe_list, parent, false)
            // TODO: set onclick listener for recycler view for viewing a full recipe
            view.setOnClickListener(onClickListener)
            return RecipeHolder(view)
        }

        override fun onBindViewHolder(holder: RecipeHolder, position: Int) {

            val recipe = recipeByIng[position]
            for (name in recipe.usedIngredients.iterator()) {
                holder.ingredients.text = "Ingredients used from your list: ${name.name}"
            }
            holder.recipeName.text = "Recipe: ${recipe.title}"
            holder.missedIngCt.text = "No. Missing Ingredients: ${recipe.missedIngredientCount}"
            holder.usedIngCt.text = "No. Used Ingredients: ${recipe.usedIngredientCount}"
            holder.recipeID.text = recipe.id.toString()
        }

        override fun getItemCount(): Int {
            return recipeByIng.size
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        // Create and populate the recycler view
        dbHelper = DBHelper(this)
        recyclerView = findViewById<RecyclerView>(R.id.recipeRV)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Call to retrieve all ingredient names from ingredients table to use as query string
        retrieveIngName()

        //TODOd: Create SpoonacularAPI object to make api calls
        val spnAPI = SpoonacularAPI.create()
        //concatenate ingredient names from ingredients table into query string
        var query = ""
        for(name in ingredientNames){
            query += "${name.replace("\\s".toRegex(), "")},"
        }
        val apiKey = BuildConfig.API_KEY //reference to hidden API key in local.properties

        //TODOd: Call to spn to get recipes by ingredients
        val call = spnAPI.findByIngredients(apiKey, query, 10, 1)
        call.enqueue(this)

        //TODO: Call to spn by recipe id to get url and send user to external url via intents

        // ON click of newSearch button, return to SelectIngredients Activity
        val newSearchBtn: Button = findViewById(R.id.newSearchBtn)
        newSearchBtn.setOnClickListener {
            val intent = Intent(this, SelectIngredientsActivity::class.java)
            startActivity(intent)
        }
    }

    //SpoonacularApi response is an array of objects, so the response using retrofit needs to be an array of Response types
    override fun onResponse(
        call: Call<Array<Response>?>,
        response: retrofit2.Response<Array<Response>?>
    ) {
        val res = response.body()!!
        // if response not null passes the result set into the recipe adapter to display the resulting data in the view elements
        if(res !=null){

            recyclerView.adapter = RecipeAdapter(res, this)
            // grab a reference to the recipe ID.
            for(response in res.iterator()){
                recipeID = response.id.toString()
                println("RECIPE ID: $recipeID")
            }
        } else{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setMessage("Your search returned no results :(")
            alertDialogBuilder.setCancelable(true)
            alertDialogBuilder.show()
        }

    }

    // Prints/Displays a corresponding error message if an error occurs.
    override fun onFailure(call: Call<Array<Response>?>, t: Throwable) {
        println("ERROR: ${t.message}")
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage("There was an error with your search :(\nPlease Try Again\n${t.message}")
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.show()
    }


    // for all checked checkboxes inside
    // this function should query the database for the ingredient names; a list of ingredient names should be returned
    fun retrieveIngName(): MutableList<String> {

        db = dbHelper.readableDatabase
        val columns =
            arrayOf<String>("rowid, name, location, quantity, unit, addedDate, updatedDate")
        val cursor = db.query(
            "ingredients",
            columns,
            null,
            null,
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                val id = getInt(0)
                val name = getString(1)
                val location = getInt(2)
                val quantity = getInt(3)
                val unit = getString(4)
                val addedDate = ISO_FORMAT.parse(getString(5))
                val updatedDate = ISO_FORMAT.parse(getString(6))
                val ingredient =
                    Ingredient(id, name, location, quantity, unit, addedDate, updatedDate)
                ingredientNames.add(ingredient.ingQueryBuilder())
            }
        }
        return ingredientNames
    }

    // TODO: Pass recipe id of clicked recipe to new Activity RecipeByIDActivity in intent and start activity
    override fun onClick(view: View?) {
        if (view != null) {
            val recipeID = view.findViewById<TextView>(R.id.recipeID).text.toString().toInt()
            val intent = Intent(this, RecipeInfoActivity::class.java)
            intent.putExtra("recipeID", recipeID)
            startActivity(intent)
        }
    }

}



