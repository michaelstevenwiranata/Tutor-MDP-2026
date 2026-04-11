package com.example.tutorw5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorw5.Element
import com.example.tutorw5.databinding.ItemElementBinding

class ElementDiffUtil : DiffUtil.ItemCallback<Element>() {
    override fun areItemsTheSame(oldItem: Element, newItem: Element): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Element, newItem: Element): Boolean = oldItem == newItem
}

var elementDiffUtil = ElementDiffUtil()
class ElementAdapter : ListAdapter<Element, ElementAdapter.ElementViewHolder>(elementDiffUtil) {

    inner class ElementViewHolder(val binding: ItemElementBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val binding = ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        val element = getItem(position)
        holder.binding.tvElementName.text = element.name
        holder.binding.tvElementDesc.text = element.description
        holder.binding.ivElementIcon.setImageResource(element.imageUrl)
    }



}