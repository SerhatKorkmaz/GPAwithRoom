package com.example.gpacalculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.gpacalculator.R
import com.example.gpacalculator.databinding.FragmentAdduserBinding
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.vm.UserViewModel

class AddUserFragment : Fragment(R.layout.fragment_adduser){

    private lateinit var userViewModel : UserViewModel

    private var _binding : FragmentAdduserBinding? = null
    private val binding get() = _binding!!

    companion object{
        fun newInstance() : AddUserFragment { return AddUserFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        return inflater.inflate(R.layout.fragment_adduser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentAdduserBinding.bind(view)

        binding.apply {
            while (tvDepartmentText.text.isEmpty() || tvGpaText.text.isEmpty() || tvNameText.text.isEmpty())
                bAdduser.isEnabled = false

                binding.bAdduser.setOnClickListener{
                    val newUser = User(0,tvNameText.text.toString(),tvDepartmentText.text.toString(),tvGpaText.text.toString() as Float)
                    userViewModel.addUser(newUser)
                    Toast.makeText(requireContext(), "New User Created", Toast.LENGTH_LONG).show()

                    val action = AddUserFragmentDirections.actionAddUserFragment2ToWelcomeFragment()
                    findNavController().navigate(action)
                }
        }
    }

}