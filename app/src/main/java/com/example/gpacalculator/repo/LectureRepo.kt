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
    var allLecturesinSemester1 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 1)
    var allLecturesinSemester2 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 2)
    var allLecturesinSemester3 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 3)
    var allLecturesinSemester4 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 4)
    var allLecturesinSemester5 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 5)
    var allLecturesinSemester6 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 6)
    var allLecturesinSemester7 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 7)
    var allLecturesinSemester8 : LiveData<List<Lecture>> = lectureDao.getLecturesofStudentinSemester(currentUserID, 8)

    fun deleteLecturesof(id : Int){
        lectureDao.deleteByUserId(id)
    }

    fun deleteLecture(id : Int){
        lectureDao.deleteCourse(id)
    }

    fun addLecture(lecture : Lecture){
        lectureDao.addLecture(lecture)
    }
}