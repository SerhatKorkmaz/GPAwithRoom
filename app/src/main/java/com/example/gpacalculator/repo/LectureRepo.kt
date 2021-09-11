package com.example.gpacalculator.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gpacalculator.currentUserID
import com.example.gpacalculator.dao.LectureDao
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.selectedSemester

class LectureRepo(private val lectureDao : LectureDao) {

    val allLecturesofStudent : LiveData<List<Lecture>> = lectureDao.getAllLecturesofStudent(currentUserID)
    val allLecturesinSemester : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, selectedSemester)

}