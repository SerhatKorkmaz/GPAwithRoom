package com.example.gpacalculator.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gpacalculator.*
import com.example.gpacalculator.adapter.LectureAdapter
import com.example.gpacalculator.databinding.FragmentGradesBinding
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.vm.LectureViewModel
import com.example.gpacalculator.vm.UserViewModel

class GradesFragment : Fragment(R.layout.fragment_grades), LectureAdapter.OnItemClickListener {

    private val args by navArgs<GradesFragmentArgs>()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private lateinit var viewmodel : LectureViewModel
    private lateinit var usermodel : UserViewModel
    private lateinit var adapter : LectureAdapter
    private var _binding : FragmentGradesBinding? = null
    private val binding get() = _binding!!
    var leclist : List<Lecture> = emptyList()
    var currentList : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(LectureViewModel::class.java)
        usermodel = ViewModelProvider(this).get(UserViewModel::class.java)
        adapter = LectureAdapter(this, ::deleteCourse)
        arrayAdapter = ArrayAdapter(requireContext(),R.layout.item_spinner,semesterList)
        Log.d("Tasks", "Grades Fragment Created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Tasks", "Grades Fragment is visible")
        _binding = FragmentGradesBinding.bind(view)

        binding.apply {
            spinner.adapter = arrayAdapter
            recyclerviewlecture.adapter = adapter
            recyclerviewlecture.layoutManager = LinearLayoutManager(requireContext())
            if(interracted) spinner.setSelection(selectedSemester-1)
            else interracted = true
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedSemester = position+1
                if(selectedSemester == 1){
                    if(viewmodel.allLecturesinSemester1.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester1.value!!)
                }
                else if(selectedSemester == 2){
                    if(viewmodel.allLecturesinSemester2.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester2.value!!)
                }
                else if(selectedSemester == 3){
                    if(viewmodel.allLecturesinSemester3.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester3.value!!)
                }
                else if(selectedSemester == 4){
                    if(viewmodel.allLecturesinSemester4.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester4.value!!)
                }
                else if(selectedSemester == 5){
                    if(viewmodel.allLecturesinSemester5.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester5.value!!)
                }
                else if(selectedSemester == 6){
                    if(viewmodel.allLecturesinSemester6.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester6.value!!)
                }
                else if(selectedSemester == 7){
                    if(viewmodel.allLecturesinSemester7.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester7.value!!)
                }
                else if(selectedSemester == 8){
                    if(viewmodel.allLecturesinSemester8.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesinSemester8.value!!)
                }
                else if(selectedSemester == 9){
                    if(viewmodel.allLecturesofStudent.value == null) adapter.setData(emptyList())
                    else adapter.setData(viewmodel.allLecturesofStudent.value!!)
                }
                viewmodel.calculateGPA()
                Log.d("Tasks", "Selected semester = ${selectedSemester}")
            }

        }

            viewmodel.allLecturesinSemester1.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester2.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester3.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester4.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester5.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester6.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester7.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesinSemester8.observe(viewLifecycleOwner, Observer {lectures->
                viewmodel.calculateGPA()
                adapter.setData(lectures)
            })

            viewmodel.allLecturesofStudent.observe(viewLifecycleOwner, Observer {
                Log.d("Tasks", "All List Changed")
                viewmodel.calculateCGPA()
                Log.d("Tasks", "${viewmodel.CGPA}")
                if(viewmodel.CGPA == null) usermodel.updateUser(currentUserID,0.0)
                else usermodel.updateUser(currentUserID,viewmodel.CGPA)
                if(viewmodel.allLecturesofStudent.value == null) adapter.setData(emptyList())
                else adapter.setData(viewmodel.allLecturesofStudent.value!!)
            })

            viewmodel.currentCredits.observe(viewLifecycleOwner, Observer { currentCredits ->
                tvSemestercredits.text = currentCredits.toString()
            })

            viewmodel.cumulativeCredits.observe(viewLifecycleOwner, Observer { cumulativeCredits ->
                tvTotalCredits.text = cumulativeCredits.toString()
            })

            viewmodel.currentGPA.observe(viewLifecycleOwner, Observer { GPA ->
                if (GPA < 2 || GPA.isNaN() ) tvGpa.setTextColor(Color.RED)
                else tvGpa.setTextColor(Color.GREEN)
                if(!GPA.isNaN() ) tvGpa.text = String.format("%.2f", GPA) else tvGpa.text = 0.0.toString()
            })

            viewmodel.cumulativeGPA.observe(viewLifecycleOwner, Observer { CGPA ->
                if (CGPA < 2 || CGPA.isNaN() ) tvCgpa.setTextColor(Color.RED)
                else tvCgpa.setTextColor(Color.GREEN)
                if(!CGPA.isNaN()) tvCgpa.text = String.format("%.2f", CGPA) else tvCgpa.text = 0.0.toString()
            })

            bAddcourse.setOnClickListener {
                val action = GradesFragmentDirections.actionGradesFragmentToAddCourseFragment()
                findNavController().navigate(action)
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
            viewmodel.calculateCGPA()
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

