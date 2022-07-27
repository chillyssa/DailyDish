package edu.msudenver.dailydish
/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - DB Helper class for Ingredients DB test table ingredients
 */

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), Serializable {

    companion object {
        const val DATABASE_NAME = "Ingredients.db"
        const val DATABASE_VERSION = 1
        val ISO_FORMAT = SimpleDateFormat("yyyy-MM-dd")
        val USA_FORMAT = SimpleDateFormat("MM/dd/yyyy")
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // create the table
        db?.execSQL("""
            CREATE TABLE ingredients ( 
                name        TEXT PRIMARY KEY, 
                location    INTEGER NOT NULL, 
                quantity    INTEGER NOT NULL, 
                unit        TEXT, 
                addedDate   TEXT NOT NULL, 
                updatedDate TEXT)
        """)
        val currentDate = ISO_FORMAT.format(Date())
        // populate the table with a few items
        db?.execSQL("""
            INSERT INTO ingredients VALUES 
                ("flour", ${Ingredient.PANTRY}, 5, "pound(s)", "$currentDate", "$currentDate"),
                ("milk", ${Ingredient.REFRIGERATOR}, 1, "gallon", "$currentDate", "$currentDate"), 
                ("granulated sugar", ${Ingredient.PANTRY}, 1, "pound(s)", "$currentDate", "$currentDate"),
                ("butter", ${Ingredient.REFRIGERATOR}, 2, "pound(s)", "$currentDate", "$currentDate"),
                ("baking soda", ${Ingredient.PANTRY}, 1, "box", "$currentDate", "$currentDate"), 
                ("baking powder", ${Ingredient.PANTRY}, 1, "container", "$currentDate", "$currentDate"),
                ("chocolate chips", ${Ingredient.PANTRY}, 12, "ounces", "$currentDate", "$currentDate"), 
                ("brown sugar", ${Ingredient.FREEZER}, 1, "pound(s)", "$currentDate", "$currentDate")
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // drop the table
        db?.execSQL("""
            DROP TABLE IF EXISTS ingredients
        """)

        // then call "onCreate" again
        onCreate(db)
    }
}