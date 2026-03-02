package com.example.minggu3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minggu3.databinding.ItemStudentBinding

class StudentAdapter(
    private var students: MutableList<Student>,
    private val onItemClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    inner class StudentViewHolder(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.student = student
            binding.executePendingBindings()

            binding.root.setOnClickListener {
                onItemClick(student)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(students[position])
    }

    override fun getItemCount(): Int = students.size

    // Memperbarui data list dan memberitahu RecyclerView untuk re-render
    fun updateList(newList: List<Student>) {
        students = newList.toMutableList()
        notifyDataSetChanged()
    }
}

