package edu.msudenver.dailydish.Models

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
    var addedDate: Date,
    var updatedDate: Date) {

    companion object {
        const val PANTRY = 0
        const val REFRIGERATOR = 1
        const val FREEZER = 2


        val LOCATION_DESCRIPTIONS = arrayOf(
            "Pantry",
            "Refrigerator",
            "Freezer"
        )

    }

    fun categoryAsString(): String? {
        return LOCATION_DESCRIPTIONS[location]
    }

    fun ingQueryBuilder(): String{
        return "${name.trim()}"
    }


    override fun toString(): String {
        return "Ingredient(name='$name', location=$location, quantity=$quantity, unit='$unit, added data=$addedDate, updated date=$updatedDate')"
        //TO-DO: add added date=$addedDate, updated date= $updatedDate to toString return call
    }




}