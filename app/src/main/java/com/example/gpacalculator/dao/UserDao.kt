package com.example.gpacalculator.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.gpacalculator.dc.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user : User)

    @Query("SELECT * FROM user_table ORDER BY user_id")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM user_table WHERE user_id = :userId")
    fun deleteByUserId(userId: Int)

    @Query("SELECT * FROM user_table WHERE user_id = :userId")
    fun findUser(userId: Int) : User
}