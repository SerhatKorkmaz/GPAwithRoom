package com.example.gpacalculator.dc

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    @PrimaryKey(autoGenerate = true)
    val user_id : Int,
    val user_name : String,
    val user_department : String,
    val user_CGPA : Float
)