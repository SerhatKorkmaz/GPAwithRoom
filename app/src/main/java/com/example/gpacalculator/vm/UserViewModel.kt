package com.example.gpacalculator.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gpacalculator.db.DataBase
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.repo.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {


    val allUsers : LiveData<List<User>>
    private val repository : UserRepo

    init {
        val userDao = DataBase.getDatabase(application).userDao()
        repository = UserRepo(userDao)
        allUsers = repository.allUsers
    }

    fun addUser(user : User){
        viewModelScope.launch { Dispatchers.IO
            repository.addUser(user)
        }
    }

    fun deleteUser(id : Int){
        viewModelScope.launch { Dispatchers.IO
            repository.deleteUser(id)
        }
    }
}