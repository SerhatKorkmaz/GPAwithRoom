package com.example.gpacalculator.repo

import androidx.lifecycle.MutableLiveData
import com.example.gpacalculator.dao.UserDao
import com.example.gpacalculator.dc.User

class UserRepo(private val userDao: UserDao) {

    val allUsers : MutableLiveData<List<User>> = userDao.getAllUsers()

    suspend fun addUser(user : User){
        userDao.addUser(user)
    }
}