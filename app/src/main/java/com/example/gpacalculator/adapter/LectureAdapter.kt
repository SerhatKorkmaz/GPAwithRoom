package com.example.gpacalculator.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gpacalculator.databinding.ItemLectureBinding
import com.example.gpacalculator.databinding.ItemUserBinding
import com.example.gpacalculator.dc.Lecture
import com.example.gpacalculator.dc.User

class LectureAdapter(private val listener : OnItemClickListener, private val onDeleteCourseCallback: (Lecture) -> Unit) : RecyclerView.Adapter<LectureAdapter.LectureViewHolder>(){

    private var lectureList = emptyList<Lecture>()

    inner class LectureViewHolder (val binding : ItemLectureBinding) : RecyclerView.ViewHolder(binding.root),
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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureViewHolder {
        val binding = ItemLectureBinding.inflate(LayoutInflater.from(parent.context), parent , false)

        return  LectureViewHolder(binding);
    }

    override fun onBindViewHolder(holder: LectureViewHolder, position: Int) {
        with(holder){
            with(lectureList[position]){
                binding.apply {
                    tvCourseCode.setText(lectureList[position].course_code.toString())
                    tvCredits.setText(lectureList[position].credits.toString())
                    tvLetterGrade.setText(lectureList[position].letter_grade.toString())

                    bDelete.setOnClickListener{
                        Log.d("Tasks", "Callback has sent")
                        binding.root.setOnClickListener { onDeleteCourseCallback(lectureList[position]) }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return lectureList.size
    }

    fun setData(lectures : List<Lecture>){
        this.lectureList = lectures
        notifyDataSetChanged()
    }
}