package com.example.gpacalculator.fragment

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gpacalculator.R
import com.example.gpacalculator.databinding.FragmentAdduserBinding
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.setWidthPercent
import com.example.gpacalculator.vm.UserViewModel

class AddUserDialogFragment : DialogFragment(R.layout.fragment_adduser){

    private lateinit var userViewModel : UserViewModel
    private var _binding : FragmentAdduserBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = FragmentAdduserBinding.inflate(LayoutInflater.from(context))
        Log.d("Tasks", "Add User Fragment Created")
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWidthPercent(99)
        Log.d("Tasks", "Add User Fragment Dialog is visible")
        _binding = FragmentAdduserBinding.bind(view)

        binding.apply {

                bAdduser.setOnClickListener{
                    Log.d("Tasks", "Clicked on Add Button")
                    if (tvDepartmentText.text.isEmpty() || tvNameText.text.isEmpty())
                        Toast.makeText(requireContext(), "You need to fill all the fields to add a user", Toast.LENGTH_LONG).show()

                    else{
                        val newUser = User(0,tvNameText.text.toString(),tvDepartmentText.text.toString(),0.0F)
                        userViewModel.addUser(newUser)
                        Toast.makeText(requireContext(), "New User Created", Toast.LENGTH_LONG).show()

                        Log.d("Tasks", "Turning back to User Fragment")
                        dismiss()
                    }

                }

                bCancel.setOnClickListener{
                    dismiss()
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}