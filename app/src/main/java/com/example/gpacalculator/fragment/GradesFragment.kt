package com.example.gpacalculator.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gpacalculator.R
import com.example.gpacalculator.adapter.LectureAdapter
import com.example.gpacalculator.adapter.UserAdapter
import com.example.gpacalculator.databinding.FragmentGradesBinding
import com.example.gpacalculator.databinding.FragmentWelcomeBinding
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

        adapter = LectureAdapter(this)
        Log.d("Tasks", "Grades Fragment Created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Tasks", "Grades Fragment is visible")
        _binding = FragmentGradesBinding.bind(view)

        binding.recyclerviewlecture.adapter = adapter
        binding.recyclerviewlecture.layoutManager = LinearLayoutManager(requireContext())
        viewmodel.allLecturesinSemester.observe(viewLifecycleOwner, Observer { lectures->
            adapter.setData(lectures)
        })
    }

    override fun onItemClick(position: Int) {

    }
}