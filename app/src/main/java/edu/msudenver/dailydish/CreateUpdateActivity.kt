package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Create and Update Activity for user ingredients list
 */

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class CreateUpdateActivity : AppCompatActivity(), View.OnClickListener {

    var op = CREATE_OP
    var id = 0
    lateinit var db: SQLiteDatabase
    lateinit var edtDescription: EditText
    lateinit var updateId: TextView
    private val ISO_FORMAT = DBHelper.ISO_FORMAT

    companion object {
        const val CREATE_OP = 0
        const val UPDATE_OP = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_update)

        val edtIngredientName = findViewById<EditText>(R.id.edtIngredientName)
        val edtIngredientAmount= findViewById<EditText>(R.id.edtIngredientAmount)
        val editIngredientUnit= findViewById<EditText>(R.id.edtIngredientUnit)
        val spnIngredientLocation = findViewById<Spinner>(R.id.spnIngredientLocation)
        val ingredientUpdateId= findViewById<TextView>(R.id.ingredientUpdateID)

        spnIngredientLocation.adapter = ArrayAdapter<String>(this, R.layout.spinner_ingredients, Ingredient.LOCATION_DESCRIPTIONS)
        spnIngredientLocation.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, ud: Long) {
                id = position
            }
        }


        val btnCreateUpdate: Button = findViewById(R.id.btnCreateUpdate)
        btnCreateUpdate.setOnClickListener(this)

        val dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase
        op = intent.getIntExtra("op", CREATE_OP)


        if (op == CREATE_OP) {
            btnCreateUpdate.text = "CREATE INGREDIENT"
           // spnIngredientLocation.setSelection(Ingredient.LOCATION_DESCRIPTIONS)
           // spnIngredientLocation.isEnabled = false

        }

        else {
            btnCreateUpdate.text = "UPDATE INGREDIENT"
            val name = intent.getIntExtra("rowid",-1) ?: ""
            //edtIngredientName.text=name
            //edtIngredientName.isEnabled = false
         //   val item = retrieveItem(name)
         //   spnCategory.setSelection(item.category)
          //  edtQuantity.setText(item.quantity.toString())
           // edtUnit.setText(item.unit)
        }





    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}