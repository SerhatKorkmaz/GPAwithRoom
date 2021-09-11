package com.example.gpacalculator.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gpacalculator.R
import com.example.gpacalculator.adapter.UserAdapter
import com.example.gpacalculator.databinding.FragmentWelcomeBinding
import com.example.gpacalculator.vm.UserViewModel

class WelcomeFragment : Fragment(R.layout.fragment_welcome),UserAdapter.OnItemClickListener {

    private lateinit var viewmodel : UserViewModel
    private lateinit var adapter : UserAdapter
    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentWelcomeBinding.bind(view)

        adapter = UserAdapter(this)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewmodel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewmodel.allUsers.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)
        })

        binding.bAdd.setOnClickListener{
            val action = WelcomeFragmentDirections.actionWelcomeFragmentToAddUserFragment2()
            findNavController().navigate(action)
        }

    }

    override fun onItemClick(position: Int) {

    }
}