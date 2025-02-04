package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Create and Update Activity for user ingredients list
 */

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.msudenver.dailydish.Models.DBHelper
import edu.msudenver.dailydish.Models.Ingredient
import kotlinx.android.synthetic.main.activity_create_update.*
import java.util.*

class CreateUpdateActivity : AppCompatActivity(), View.OnClickListener {

    var op = CREATE_OP
    var id = 0
    lateinit var ingredient: Ingredient
    lateinit var db: SQLiteDatabase
    private val ISO_FORMAT = DBHelper.ISO_FORMAT

    companion object {
        const val CREATE_OP = 0
        const val UPDATE_OP = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update)

        // Get a reference to all view elements
        val edtIngredientName = findViewById<EditText>(R.id.edtIngredientName)
        val edtIngredientAmount = findViewById<EditText>(R.id.edtIngredientAmount)
        val edtIngredientUnit = findViewById<EditText>(R.id.edtIngredientUnit)
        val spnIngredientLocation = findViewById<Spinner>(R.id.spnIngredientLocation)

        // Spinner adapter for the location descriptions spinner element
        spnIngredientLocation.adapter = ArrayAdapter<String>(
            this,
            R.layout.spinner_ingredients,
            Ingredient.LOCATION_DESCRIPTIONS
        )

        spnIngredientLocation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                ud: Long
            ) {
                id = position
            }
        }

        // Reference to the create/update button
        val btnCreateUpdate: Button = findViewById(R.id.btnCreateUpdate)
        btnCreateUpdate.setOnClickListener(this)

        // Create a writable dbHelper to insert/update rows in ingredients table
        val dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase
        op = intent.getIntExtra("op", CREATE_OP)

        // Based on the selection set the button text and view elements accordingly
        if (op == CREATE_OP) {
            btnCreateUpdate.text = "CREATE INGREDIENT"
            spnIngredientLocation.setSelection(0)
            spnIngredientLocation.isEnabled = true
        } else {
            btnCreateUpdate.text = "UPDATE INGREDIENT"
            val id = intent.getIntExtra("rowid", -1)
            ingredient = retrieveRecord(id)
            edtIngredientName.setText(ingredient.name)
            edtIngredientAmount.setText(ingredient.quantity.toString())
            edtIngredientUnit.setText(ingredient.unit)
            spnIngredientLocation.setSelection(ingredient.location)
            spnIngredientLocation.isEnabled = false
        }
    }

    fun retrieveRecord(id: Int): Ingredient {
        val cursor = db.query("ingredients", null, "rowid=$id", null, null, null, null)
        with(cursor) {
            moveToNext()
            val name = getString(getColumnIndexOrThrow("name"))
            val location = getInt(getColumnIndexOrThrow("location"))
            val quantity = getInt(getColumnIndexOrThrow("quantity"))
            val unit = getString(getColumnIndexOrThrow("unit"))
            val addedDate = DBHelper.ISO_FORMAT.parse(getString(getColumnIndexOrThrow("addedDate")))
            val updatedDate =
                DBHelper.ISO_FORMAT.parse(getString(getColumnIndexOrThrow("updatedDate")))
            return Ingredient(id, name, location, quantity, unit, addedDate!!, updatedDate!!)
        }
    }

    // On click of the create/update button, save the values and insert/update in the ingredients table
    override fun onClick(v: View?) {
        val value = ContentValues()
        value.put("name", edtIngredientName.text.toString())
        value.put("location", spnIngredientLocation.selectedItemId)
        value.put("quantity", Integer.parseInt(edtIngredientAmount.text.toString()))
        value.put("unit", edtIngredientUnit.text.toString())
        value.put("updatedDate", ISO_FORMAT.format(Calendar.getInstance().time))
        if (op == CREATE_OP) {
            value.put("addedDate", ISO_FORMAT.format(Calendar.getInstance().time))
            db.insert("ingredients", null, value)
        } else {
            value.put("addedDate", ISO_FORMAT.format(ingredient.addedDate))
            db.update("ingredients", value, "rowid=${ingredient.id}", null)
        }
        finish()
    }
}