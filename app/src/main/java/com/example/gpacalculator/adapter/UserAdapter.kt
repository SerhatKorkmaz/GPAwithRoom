package com.example.gpacalculator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.gpacalculator.databinding.ItemUserBinding
import com.example.gpacalculator.dc.User
import com.example.gpacalculator.vm.UserViewModel



class UserAdapter(private val listener : OnItemClickListener,private val onDeleteCallback: (User) -> Unit) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    private var userList = emptyList<User>()

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position, userList[position])
            }

        }

        init{
            itemView.setOnClickListener(this)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, user : User)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent , false)

        return  UserViewHolder(binding);
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        with(holder){
            with(userList[position]){
                binding.apply {
                    tvNameText.text = userList[position].user_name
                    tvDepartmentText.text = userList[position].user_department
                    tvGpaText.text = String.format("%.2f", userList[position].user_CGPA)

                    binding.bDelete.setOnClickListener{
                        Log.d("Tasks", "Callback has sent")
                        onDeleteCallback(userList[position])
                    }
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user : List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

}