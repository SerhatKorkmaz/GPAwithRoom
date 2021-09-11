package com.example.gpacalculator.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User


@Dao
interface LectureDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLecture(lecture : Lecture)

    @Query("SELECT * FROM lectures_table ORDER BY course_id")
    fun readAllLectures(): LiveData<List<Lecture>>

    @Query("SELECT * FROM lectures_table WHERE student_id = :studentID AND semester_code = :semesterID")
    fun getLecturesofStudentinSemester(studentID: Int, semesterID: Int): LiveData<List<Lecture>>

    @Query("SELECT * FROM lectures_table WHERE student_id = :studentID")
    fun getAllLecturesofStudent(studentID: Int): LiveData<List<Lecture>>
}