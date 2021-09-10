package com.example.gpacalculator.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.gpacalculator.R
import com.example.gpacalculator.adapter.UserAdapter
import com.example.gpacalculator.databinding.FragmentWelcomeBinding
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.vm.UserViewModel

class WelcomeFragment : Fragment(R.layout.fragment_welcome),UserAdapter.OnItemClickListener {

    private val viewmodel by viewModels<UserViewModel>()

    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWelcomeBinding.bind(view)

        val adapter = UserAdapter(viewmodel.allUsers as ArrayList<User>,this)

        binding.bAdd.setOnClickListener{

        }

    }

    override fun onItemClick(position: Int) {

    }
}