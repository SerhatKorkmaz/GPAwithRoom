package com.example.gpacalculator.dc

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lectures_table")
data class Lecture (
    @PrimaryKey(autoGenerate = true)
    val course_id : Int,
    val course_code : String,
    val credits : Int,
    val letter_grade : String,
    val semester_code : Int
)