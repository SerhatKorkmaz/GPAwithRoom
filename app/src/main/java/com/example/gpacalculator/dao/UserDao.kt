package com.example.gpacalculator.dao

import androidx.lifecycle.LiveData
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
    fun readAllUsers(): LiveData<List<User>>

    //@Query("DELETE FROM user_table WHERE user_id = )
}