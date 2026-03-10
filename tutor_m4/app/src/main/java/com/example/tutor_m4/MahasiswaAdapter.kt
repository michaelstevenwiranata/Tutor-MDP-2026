package com.example.tutor_m4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutor_m4.databinding.ItemMahasiswaBinding
import com.example.tutor_m4.Mahasiswa

class MahasiswaAdapter (
    private val onClick: (Mahasiswa) -> Unit
):
    ListAdapter<Mahasiswa, MahasiswaAdapter.ViewHolder>(DIFF_CALLBACK) {

    inner class ViewHolder(val binding: ItemMahasiswaBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemMahasiswaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val mahasiswa = getItem(position)

        holder.binding.txtNama.text = mahasiswa.nama
        holder.binding.txtNRP.text = mahasiswa.nrp
        holder.binding.imgMahasiswa.setImageResource(mahasiswa.foto)

        holder.itemView.setOnClickListener {
            onClick(mahasiswa)
        }
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Mahasiswa>() {

            override fun areItemsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
                return oldItem.nrp == newItem.nrp
            }

            override fun areContentsTheSame(oldItem: Mahasiswa, newItem: Mahasiswa): Boolean {
                return oldItem == newItem
            }
        }
    }
}