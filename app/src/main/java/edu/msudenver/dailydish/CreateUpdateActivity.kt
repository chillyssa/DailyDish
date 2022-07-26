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
import android.widget.EditText
import android.widget.TextView

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


    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}