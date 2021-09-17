package com.example.gpacalculator.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gpacalculator.R
import com.example.gpacalculator.currentUserID
import com.example.gpacalculator.databinding.FragmentAddcourseBinding
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.selectedSemester
import com.example.gpacalculator.vm.LectureViewModel
import com.example.gpacalculator.vm.UserViewModel

class AddCourseFragment : Fragment(R.layout.fragment_addcourse) {

    private lateinit var lectureViewModel: LectureViewModel
    private lateinit var userViewModel: UserViewModel
    private var _binding : FragmentAddcourseBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Tasks", "Add User Fragment Created")
        lectureViewModel = ViewModelProvider(this).get(LectureViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("Tasks", "Add User Fragment is visible")
        _binding = FragmentAddcourseBinding.bind(view)

        binding.apply {

            val user = userViewModel.getUser(currentUserID)
            tvStudentName.text = user.user_name
            tvSemester.text = selectedSemester.toString()

            binding.bAddcourse.setOnClickListener{

                if (tvCourseCode.text.isEmpty() || tvCredits.text.isEmpty() || tvLetterGrade.text.isEmpty())
                    Toast.makeText(requireContext(), "You need to fill all the fields to add a course", Toast.LENGTH_LONG).show()

                else{
                    val newLecture = Lecture(0,tvCourseCode.text.toString(),tvCredits.text.toString().toInt(),tvLetterGrade.text.toString(), selectedSemester, currentUserID)
                    lectureViewModel.addCourse(newLecture)
                    Toast.makeText(requireContext(), "New Lecture Added to List", Toast.LENGTH_LONG).show()
                    Log.d("Tasks", "New Lecture Added to Semester ${selectedSemester}")
                    Log.d("Tasks", "Navigating to Welcome Fragment")
                    val action = AddCourseFragmentDirections.actionAddCourseFragmentToGradesFragment(userViewModel.getUser(currentUserID))
                    findNavController().navigate(action)
                }

            }
        }
    }

}