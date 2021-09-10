package com.example.gpacalculator.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gpacalculator.dc.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user : User)

    @Query("SELECT * FROM user_table ORDER BY user_id")
    fun getAllUsers(): MutableLiveData<List<User>>
}