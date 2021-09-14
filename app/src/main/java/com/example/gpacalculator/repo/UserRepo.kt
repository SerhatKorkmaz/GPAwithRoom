package com.example.gpacalculator.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gpacalculator.dao.UserDao
import com.example.gpacalculator.dc.User

class UserRepo(private val userDao: UserDao) {

    val allUsers : LiveData<List<User>> = userDao.getAllUsers()

    fun addUser(user : User){
        userDao.addUser(user)
    }

    fun deleteUser(id : Int){
        userDao.deleteByUserId(id)
    }
}