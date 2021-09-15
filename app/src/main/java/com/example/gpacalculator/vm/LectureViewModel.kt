package com.example.gpacalculator.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gpacalculator.currentUserID
import com.example.gpacalculator.db.DataBase
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.repo.LectureRepo
import com.example.gpacalculator.selectedSemester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LectureViewModel(application: Application) : AndroidViewModel(application) {

    var allLecturesofStudent : LiveData<List<Lecture>>
    var allLecturesinSemester : LiveData<List<Lecture>>
    private val repository : LectureRepo
    private val lectureDao = DataBase.getDatabase(application).lectureDao()

    val currentGPA : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val cumulativeGPA : MutableLiveData<Double> by lazy {
        MutableLiveData<Double>()
    }

    val currentCredits : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    val cumulativeCredits : MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    var GPA : Double = 0.0
    var CGPA : Double = 0.0
    init {
        repository = LectureRepo(lectureDao)
        allLecturesinSemester = repository.allLecturesinSemester
        allLecturesofStudent = repository.allLecturesofStudent
    }

    fun addCourse(lecture : Lecture){
        viewModelScope.launch { Dispatchers.IO
            repository.addLecture(lecture)
            updateCourseList()
        }
    }

    fun deleteLecturesOf(id : Int){
        viewModelScope.launch { Dispatchers.IO
            repository.deleteLecturesof(id)
        }
    }

    fun deleteLecture(id : Int){
        viewModelScope.launch { Dispatchers.IO
            repository.deleteLecture(id)
        }
    }

    fun updateCourseList(){
        viewModelScope.launch { Dispatchers.IO
            repository.updatelist()
            allLecturesinSemester = repository.allLecturesinSemester
            allLecturesofStudent = repository.allLecturesofStudent
        }
    }

    fun calculateCGPA(currentSemester: Int) {
        var ctotalCredits: Int = 0
        var ctotalWeight: Double = 0.0
        var totalCredits: Int = 0
        var totalWeight: Double = 0.0
        val clectures: List<Lecture>? = allLecturesofStudent.value
        val lectures: List<Lecture>? = allLecturesinSemester.value
        Log.d("Tasks", "Current size of Semester ${selectedSemester} List is ${lectures?.size}")
        Log.d("Tasks", "Current size of Semester All List is ${allLecturesofStudent.value?.size}")
        if (clectures != null) {
            for (item in clectures) {
                ctotalCredits += item.credits
                if (item.letter_grade == "AA") ctotalWeight += item.credits * 4
                else if (item.letter_grade == "BA") ctotalWeight += item.credits * 3.5
                else if (item.letter_grade == "BB") ctotalWeight += item.credits * 3
                else if (item.letter_grade == "CB") ctotalWeight += item.credits * 2.5
                else if (item.letter_grade == "CC") ctotalWeight += item.credits * 2
                else if (item.letter_grade == "DC") ctotalWeight += item.credits * 1.5
                else if (item.letter_grade == "DD") ctotalWeight += item.credits * 1
                else if (item.letter_grade == "FD") ctotalWeight += item.credits * 0.5
                else ctotalWeight += item.credits * 0.0
            }
            CGPA = ctotalWeight / ctotalCredits
            cumulativeGPA.value = CGPA
            cumulativeCredits.value = ctotalCredits
        }
        else{
            CGPA = 0.0
            cumulativeGPA.value = CGPA
            cumulativeCredits.value = 0
        }
        if (lectures != null) {
            for (item in lectures) {
                totalCredits += item.credits
                if (item.letter_grade == "AA") totalWeight += item.credits * 4
                else if (item.letter_grade == "BA") totalWeight += item.credits * 3.5
                else if (item.letter_grade == "BB") totalWeight += item.credits * 3
                else if (item.letter_grade == "CB") totalWeight += item.credits * 2.5
                else if (item.letter_grade == "CC") totalWeight += item.credits * 2
                else if (item.letter_grade == "DC") totalWeight += item.credits * 1.5
                else if (item.letter_grade == "DD") totalWeight += item.credits * 1
                else if (item.letter_grade == "FD") totalWeight += item.credits * 0.5
                else totalWeight += item.credits * 0.0
            }
            GPA = totalWeight / totalCredits
            currentGPA.value = GPA
            currentCredits.value = totalCredits
        }
        else{
            GPA = 0.0
            currentGPA.value = GPA
            currentCredits.value = 0
        }
    }
}

