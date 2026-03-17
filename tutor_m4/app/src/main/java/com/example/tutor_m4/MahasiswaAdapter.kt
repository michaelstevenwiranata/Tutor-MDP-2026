package com.example.tutor_m4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutor_m4.databinding.ItemMahasiswaBinding
import com.example.tutor_m4.Mahasiswa

class MahasiswaDiffUtil : DiffUtil.ItemCallback<Mahasiswa>(){
    override fun areItemsTheSame(
        oldItem: Mahasiswa,
        newItem: Mahasiswa
    ): Boolean {
        return oldItem.nrp == newItem.nrp
    }

    override fun areContentsTheSame(
        oldItem: Mahasiswa,
        newItem: Mahasiswa
    ): Boolean {
        return oldItem == newItem
    }
}

val mahasiswaDiffUtil = MahasiswaDiffUtil()

class MahasiswaAdapter (
    private val onClick: (Mahasiswa) -> Unit,
    private val onEditClick: (Mahasiswa) -> Unit,
    private val onDeleteClick: (Mahasiswa) -> Unit
):
    ListAdapter<Mahasiswa, MahasiswaAdapter.ViewHolder>(mahasiswaDiffUtil) {

    class ViewHolder(val binding: ItemMahasiswaBinding) :
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
        holder.binding.btnEdit.setOnClickListener {
            onEditClick(mahasiswa)
        }
        holder.binding.btnDelete.setOnClickListener {
            onDeleteClick(mahasiswa)
        }

        holder.itemView.setOnClickListener {
            onClick(mahasiswa)
        }
    }
}