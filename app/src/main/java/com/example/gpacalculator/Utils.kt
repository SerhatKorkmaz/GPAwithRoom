package com.example.gpacalculator

import android.content.res.Resources
import android.graphics.Rect
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

var currentUserID: Int = 0
var selectedSemester: Int = 0

val semesterList  = arrayOf(
    "I. Semester",
    "II. Semester",
    "III. Semester",
    "IV. Semester",
    "V. Semester",
    "VI. Semester",
    "VII. Semester",
    "VIII. Semester",
    "All Semesters")

var interracted : Boolean = false

fun DialogFragment.setWidthPercent(percentage: Int) {
    val percent = percentage.toFloat() / 100
    val dm = Resources.getSystem().displayMetrics
    val rect = dm.run { Rect(0, 0, widthPixels, heightPixels) }
    val percentWidth = rect.width() * percent
    dialog?.window?.setLayout(percentWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
}