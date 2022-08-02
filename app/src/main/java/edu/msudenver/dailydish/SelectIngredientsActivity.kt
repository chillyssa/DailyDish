package edu.msudenver.dailydish

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Select Ingredients Activity for user to select ingredients to search from
 */

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.msudenver.dailydish.Models.DBHelper
import edu.msudenver.dailydish.Models.Ingredient

class SelectIngredientsActivity : AppCompatActivity() {

    private lateinit var ingredientRV: RecyclerView
    private lateinit var dbHelper: DBHelper
    private val ISO_FORMAT = DBHelper.ISO_FORMAT

    // Ingredient selection holder view for the recycler view for all recipe information to display
    private inner class SelectionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ingCB: CheckBox = view.findViewById(R.id.ingCB)
    }

    //SelectionAdapter uses the Response data to set the view to the ingredients in the ingredient table
    private inner class SelectionAdapter(var ingredientList: List<Ingredient>) :
        RecyclerView.Adapter<SelectionHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectionHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.search_by_list, parent, false)
            return SelectionHolder(view)
        }

        override fun onBindViewHolder(holder: SelectionHolder, position: Int) {
            val ingredient = ingredientList[position]
            holder.ingCB.text = ingredient.name
        }

        override fun getItemCount(): Int {
            return ingredientList.size
        }
    }

    private fun populateRecyclerView() {
        val db = dbHelper.readableDatabase
        val ingredientList = mutableListOf<Ingredient>()
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
                ingredientList.add(ingredient)
            }
        }
        ingredientRV.adapter = SelectionAdapter(ingredientList)
        println("Ingredients: $ingredientList")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_ingredients)

        // Create and populate the recycler view for the ingredient checkboxes
        dbHelper = DBHelper(this)
        ingredientRV = findViewById<RecyclerView>(R.id.ingredientRV)
        ingredientRV.layoutManager = LinearLayoutManager(this)
        populateRecyclerView()

        // Initialize the get recipes button
        val getRecipesBtn: Button = findViewById(R.id.getRecipesBtn)
        getRecipesBtn.setOnClickListener {
            // pass intent to RecipeActivity on click of get recipes button
            // TODO: putExtra such that the checked ingredient names are passed with the intents
            val intent = Intent(this, RecipeActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        populateRecyclerView()
    }
}
