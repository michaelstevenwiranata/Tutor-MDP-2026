package com.example.tutorw3

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorw3.databinding.ItemExcuseBinding

class ExcuseDiffUtil : DiffUtil.ItemCallback<Excuse>() {
    override fun areItemsTheSame(oldItem: Excuse, newItem: Excuse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Excuse, newItem: Excuse): Boolean {
        return oldItem == newItem
    }
}

val excuseDiffUtil = ExcuseDiffUtil()

class ExcuseAdapter(
    private val onEditClick: (Excuse) -> Unit,
    private val onDeleteClick: (Excuse) -> Unit,
    private val onItemClick: (Excuse) -> Unit
) : ListAdapter<Excuse, ExcuseAdapter.ViewHolder>(excuseDiffUtil) {

    class ViewHolder(val binding: ItemExcuseBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemExcuseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val excuse = getItem(position)

        // Binding Data
        holder.binding.tvItemEmoji.text = excuse.emoji
        holder.binding.tvItemJudul.text = excuse.judul
        holder.binding.tvItemRisiko.text = excuse.tingkatRisiko

        // Logika Warna (Bisa ditaruh di sini karena ViewHolder bukan inner)
        val color = when (excuse.tingkatRisiko) {
            "Aman" -> "#4CAF50"
            "Bahaya" -> "#FF9800"
            "Kritis" -> "#F44336"
            else -> "#757575"
        }
        holder.binding.tvItemRisiko.setTextColor(Color.parseColor(color))

        // Setup Listeners
        holder.binding.btnEdit.setOnClickListener { onEditClick(excuse) }
        holder.binding.btnDelete.setOnClickListener { onDeleteClick(excuse) }
        holder.itemView.setOnClickListener { onItemClick(excuse) }
    }
}