package com.example.gpacalculator.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gpacalculator.R
import com.example.gpacalculator.adapter.UserAdapter
import com.example.gpacalculator.currentUserID
import com.example.gpacalculator.databinding.FragmentWelcomeBinding
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.selectedSemester
import com.example.gpacalculator.vm.LectureViewModel
import com.example.gpacalculator.vm.UserViewModel

class UserFragment : Fragment(R.layout.fragment_welcome),UserAdapter.OnItemClickListener {

    private lateinit var userviewmodel : UserViewModel
    private lateinit var lectureviewmodel : LectureViewModel
    private lateinit var adapter : UserAdapter
    private var _binding : FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userviewmodel = ViewModelProvider(this).get(UserViewModel::class.java)
        lectureviewmodel = ViewModelProvider(this).get(LectureViewModel::class.java)
        adapter = UserAdapter(this, ::deleteUser)
        Log.d("Tasks", "Welcome Fragment Created")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Tasks", "Welcome Fragment is visible")
        _binding = FragmentWelcomeBinding.bind(view)

        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

        userviewmodel.allUsers.observe(viewLifecycleOwner, Observer { user->
            adapter.setData(user)
        })

        binding.bAdd.setOnClickListener{
            Log.d("Tasks", "Creating Add User Dialog Fragment")
            var dialog = AddUserDialogFragment()
            dialog.show(childFragmentManager,"")
        }

    }

    private fun deleteUser(user: User) {
        Log.d("Tasks", "Callback received")
        Log.d("Tasks", "Deleting a User")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirm Delete")
        builder.setMessage("Are you sure you want to delete ${user.user_name} profile from the App?")
        builder.setPositiveButton("Yes",DialogInterface.OnClickListener{dialog, id ->
            userviewmodel.deleteUser(user.user_id)
            lectureviewmodel.deleteLecturesOf(user.user_id)
            dialog.cancel()
        })
        builder.setNegativeButton("No",DialogInterface.OnClickListener{dialog, id ->
            dialog.cancel()
        })
        var alert : AlertDialog = builder.create()
        alert.show()
    }

    override fun onItemClick(position: Int, user : User) {
        if(user.user_id != currentUserID) selectedSemester = 1
        currentUserID = user.user_id
        val action = UserFragmentDirections.actionWelcometoGrades(user)
        findNavController().navigate(action)
    }
}