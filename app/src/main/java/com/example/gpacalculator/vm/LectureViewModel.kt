package com.example.gpacalculator.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gpacalculator.currentUserID
import com.example.gpacalculator.db.DataBase
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.repo.LectureRepo

class LectureViewModel(application: Application) : AndroidViewModel(application) {

    val allLecturesofStudent : LiveData<List<Lecture>>
    var allLecturesinSemester : LiveData<List<Lecture>>
    private val repository : LectureRepo
    val lectureDao = DataBase.getDatabase(application).lectureDao()

    val currentGPA : MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }
    val cumulativeGPA : MutableLiveData<Float> by lazy {
        MutableLiveData<Float>()
    }

    var GPA : Float = 0.0F
    var CGPA : Float = 0.0F

    init {
        repository = LectureRepo(lectureDao)
        allLecturesinSemester = repository.allLecturesinSemester
        allLecturesofStudent = repository.allLecturesofStudent
    }

    fun calculateCGPA(){
        var totalCredits : Int = 0
        var totalWeight : Float = 0.0F
        for (course in allLecturesofStudent){

        }
    }

    fun changeSemester(currentSemester : Int){
        var totalCredits : Int = 0
        var totalWeight : Float = 0.0F
        allLecturesinSemester = lectureDao.getLecturesofStudentinSemester(currentUserID,currentSemester)
        for (item in allLecturesinSemester){

        }
    }
}