package com.example.gpacalculator.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gpacalculator.currentUserID
import com.example.gpacalculator.dao.LectureDao
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.selectedSemester

class LectureRepo(private val lectureDao : LectureDao) {

    var allLecturesofStudent : LiveData<List<Lecture>> = lectureDao.getAllLecturesofStudent(currentUserID)
    var allLecturesinSemester : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, selectedSemester)

    fun deleteLecturesof(id : Int){
        lectureDao.deleteByUserId(id)
    }

    fun deleteLecture(id : Int){
        lectureDao.deleteCourse(id)
    }

    fun updatelist(){
        allLecturesinSemester = lectureDao.getLecturesofStudentinSemester(currentUserID, selectedSemester)
        allLecturesofStudent = lectureDao.getAllLecturesofStudent(currentUserID)
    }

    fun addLecture(lecture : Lecture){
        lectureDao.addLecture(lecture)
    }
}