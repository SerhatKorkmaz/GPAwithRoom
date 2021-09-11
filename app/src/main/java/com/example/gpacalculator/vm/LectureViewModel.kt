package com.example.gpacalculator.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gpacalculator.db.DataBase
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.repo.LectureRepo

class LectureViewModel(application: Application) : AndroidViewModel(application) {

    private val allLecturesofStudent : LiveData<List<Lecture>>
    private val allLecturesinSemester : LiveData<List<Lecture>>
    private val repository : LectureRepo

    init {
        val lectureDao = DataBase.getDatabase(application).lectureDao()
        repository = LectureRepo(lectureDao)
        allLecturesinSemester = repository.allLecturesinSemester
        allLecturesofStudent = repository.allLecturesofStudent
    }
}