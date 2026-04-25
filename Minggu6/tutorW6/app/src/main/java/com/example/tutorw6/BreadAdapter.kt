package com.example.tutorw6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorw6.databinding.ItemBreadBinding
class BreadDiffCallback : DiffUtil.ItemCallback<Bread>() {
    override fun areItemsTheSame(oldItem: Bread, newItem: Bread): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Bread, newItem: Bread): Boolean {
        return oldItem == newItem
    }
}

class BreadAdapter(
    private val onBreadClick: (Int) -> Unit,
    private val onAddCartClick: (Bread) -> Unit,
    private val onEditClick: (Int) -> Unit,
    private val onDeleteClick: (Bread) -> Unit
) : ListAdapter<Bread, BreadAdapter.BreadViewHolder>(BreadDiffCallback()) {

    inner class BreadViewHolder(private val binding: ItemBreadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(bread: Bread) {
            binding.tvEmoji.text = bread.emoji
            binding.tvName.text = bread.name

            // Set aksi listener
            binding.root.setOnClickListener { onBreadClick(bread.id) }
            binding.btnAddCart.setOnClickListener { onAddCartClick(bread) }
            binding.btnEdit.setOnClickListener { onEditClick(bread.id) }
            binding.btnDelete.setOnClickListener { onDeleteClick(bread) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreadViewHolder {
        val binding = ItemBreadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BreadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreadViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}