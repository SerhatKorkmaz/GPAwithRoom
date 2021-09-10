package com.example.gpacalculator.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gpacalculator.dc.Lecture


@Dao
interface LectureDao {

    //asfasfasfasfas

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLecture(lecture : Lecture)

    @Query("SELECT * FROM lectures_table ORDER BY course_id")
    fun readAllLectures(): LiveData<List<Lecture>>
}