package com.example.tutorw5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorw5.Reaction
import com.example.tutorw5.databinding.ItemReactionBinding

class ReactionDiffUtil : DiffUtil.ItemCallback<Reaction>() {
    override fun areItemsTheSame(oldItem: Reaction, newItem: Reaction): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Reaction, newItem: Reaction): Boolean = oldItem == newItem
}

val reactionDiffUtil = ReactionDiffUtil()
class ReactionAdapter(
    private val onEditClick: (Reaction) -> Unit,
    private val onDeleteClick: (Reaction) -> Unit
) : ListAdapter<Reaction, ReactionAdapter.ViewHolder>(reactionDiffUtil) {

    class ViewHolder(val binding: ItemReactionBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val reaction = getItem(position)
        holder.binding.tvReactionName.text = "${reaction.name} (${reaction.element1.name} + ${reaction.element2.name})"
        holder.binding.tvReactionDesc.text = reaction.description
        holder.binding.btnEdit.setOnClickListener { onEditClick(reaction) }
        holder.binding.btnDelete.setOnClickListener { onDeleteClick(reaction) }
    }


}