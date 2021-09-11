package com.example.gpacalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gpacalculator.databinding.ItemUserBinding
import com.example.gpacalculator.dc.User

class UserAdapter(private val listener : OnItemClickListener) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener{
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listener.onItemClick(position)
            }
        }

        init{
            itemView.setOnClickListener(this)
        }

    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
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
                    tvGpaText.text = userList[position].user_CGPA.toString()

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