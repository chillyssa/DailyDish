package edu.msudenver.dailydish

/*
 * CS3013 - Mobile App Dev. - Summer 2022
 * Instructor: Thyago Mota
 * Student(s): Brea Chaney, Karent Correa and Alyssa Williams
 * Description: DailyDish - Ingredient (model) class for Ingredients DB
 */

import java.util.*

class Ingredient(
    var id: Int,
    var name: String,
    var location: Int,
    var quantity: Int,
    var unit: String,
    addedDate: Date,
    updatedDate: Date) {

    companion object {
        const val PANTRY = 1
        const val REFRIGERATOR = 2
        const val FREEZER = 3


        val LOCATION_DESCRIPTIONS = arrayOf( null,
            "Pantry",
            "Refrigerator",
            "Freezer"
        )

    }

    fun categoryAsString(): String? {
        return LOCATION_DESCRIPTIONS[location]
    }

    override fun toString(): String {
        return "Ingredient(name='$name', location=$location, quantity=$quantity, unit='$unit')"
        //TODO: add added date=$addedDate, updated date= $updatedDate to toString return call
    }


}