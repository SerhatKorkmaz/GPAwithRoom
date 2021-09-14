package com.example.gpacalculator.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gpacalculator.R
import com.example.gpacalculator.adapter.LectureAdapter
import com.example.gpacalculator.adapter.UserAdapter
import com.example.gpacalculator.databinding.FragmentGradesBinding
import com.example.gpacalculator.databinding.FragmentWelcomeBinding
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.selectedSemester
import com.example.gpacalculator.vm.LectureViewModel
import com.example.gpacalculator.vm.UserViewModel

class GradesFragment : Fragment(R.layout.fragment_grades), LectureAdapter.OnItemClickListener {

    private val args by navArgs<GradesFragmentArgs>()
    private lateinit var viewmodel : LectureViewModel
    private lateinit var adapter : LectureAdapter
    private var _binding : FragmentGradesBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewmodel = ViewModelProvider(this).get(LectureViewModel::class.java)
        viewmodel.calculateCGPA()
        viewmodel.changeSemester(0)
        adapter = LectureAdapter(this, ::deleteCourse)
        selectedSemester = 1
        Log.d("Tasks", "Grades Fragment Created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Tasks", "Grades Fragment is visible")
        _binding = FragmentGradesBinding.bind(view)

        binding.apply {
            recyclerviewlecture.adapter = adapter
            recyclerviewlecture.layoutManager = LinearLayoutManager(requireContext())

            viewmodel.allLecturesinSemester.observe(viewLifecycleOwner, Observer { lectures->
                adapter.setData(lectures)
            })

            viewmodel.currentCredits.observe(viewLifecycleOwner, Observer { currentCredits ->
                tvSemestercredits.text = currentCredits.toString()
            })

            viewmodel.currentGPA.observe(viewLifecycleOwner, Observer { GPA ->
                if (GPA < 2 ) tvGpa.setTextColor(Color.RED)
                else tvGpa.setTextColor(Color.GREEN)
                tvGpa.text = GPA.toString()
            })

            viewmodel.cumulativeGPA.observe(viewLifecycleOwner, Observer { CGPA ->
                if (CGPA < 2 ) tvCgpa.setTextColor(Color.RED)
                else tvCgpa.setTextColor(Color.GREEN)
                tvCgpa.text = CGPA.toString()
            })

            viewmodel.cumulativeCredits.observe(viewLifecycleOwner, Observer { cumulativeCredits ->
                tvTotalCredits.text = cumulativeCredits.toString()
            })

            bAddcourse.setOnClickListener {
                val action = GradesFragmentDirections.actionGradesFragmentToAddCourseFragment()
                findNavController().navigate(action)
            }

            b1st.setOnClickListener {
                selectedSemester = 1
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b2nd.setOnClickListener {
                selectedSemester = 2
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b3rd.setOnClickListener {
                selectedSemester = 3
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b4th.setOnClickListener {
                selectedSemester = 4
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b5th.setOnClickListener {
                selectedSemester = 5
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b6th.setOnClickListener {
                selectedSemester = 6
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b7th.setOnClickListener {
                selectedSemester = 7
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

            b8th.setOnClickListener {
                selectedSemester = 8
                viewmodel.updateCourseList()
                adapter.notifyDataSetChanged()
            }

        }
    }

    private fun deleteCourse(lecture : Lecture) {
        Log.d("Tasks", "Callback received")
        Log.d("Tasks", "Deleting a User")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Delete")
        builder.setMessage("Are you sure you want to delete ${lecture.course_code} Course from ${selectedSemester}?")
        builder.setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog, id ->
            viewmodel.deleteLecture(lecture.course_id)
            viewmodel.updateCourseList()
            viewmodel.calculateCGPA()
            adapter.notifyDataSetChanged()
            dialog.cancel()
        })
        builder.setNegativeButton("No", DialogInterface.OnClickListener{ dialog, id ->
            dialog.cancel()
        })
        var alert : AlertDialog = builder.create()
        alert.show()
    }

    override fun onItemClick(position: Int) {

    }
}

