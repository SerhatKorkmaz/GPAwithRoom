package com.example.gpacalculator.dc

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "lectures_table")
data class Lecture (
    @PrimaryKey(autoGenerate = true)
    val course_id : Int,
    val course_code : String,
    val credits : Int,
    val letter_grade : String,
    val semester_code : Int,
    val student_id : Int
) : Parcelable