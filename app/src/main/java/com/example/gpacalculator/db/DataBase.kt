package com.example.gpacalculator.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User

@Database(entities = [User::class,Lecture::class],version = 1,exportSchema = false)
abstract class DataBase : RoomDatabase() {
}