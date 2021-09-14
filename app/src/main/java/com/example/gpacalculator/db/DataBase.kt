package com.example.gpacalculator.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gpacalculator.dao.LectureDao
import com.example.gpacalculator.dao.UserDao
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User

@Database(entities = [User::class,Lecture::class],version = 1,exportSchema = false)
abstract class DataBase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun lectureDao() : LectureDao

    companion object{
        @Volatile
        private var INSTANCE : DataBase? = null

        fun getDatabase(context : Context) : DataBase{
            val temp = INSTANCE
            if(temp != null) return temp
            else{
                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "app_database"
                    ).allowMainThreadQueries().build()
                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}