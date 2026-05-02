package com.example.tutorw7

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tutorw7.databinding.ItemBucinBinding

class BucinDiffUtil : DiffUtil.ItemCallback<Bucin>() {
    override fun areItemsTheSame(oldItem: Bucin, newItem: Bucin) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Bucin, newItem: Bucin) = oldItem == newItem
}
class BucinAdapter(private val onItemClick: (Bucin) -> Unit) :
    ListAdapter<Bucin, BucinAdapter.BucinViewHolder>(BucinDiffUtil()) {
    class BucinViewHolder(val binding: ItemBucinBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BucinViewHolder {
        val binding = ItemBucinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BucinViewHolder(binding)
    }


    override fun onBindViewHolder(holder: BucinViewHolder, position: Int) {
        val bucin = getItem(position)
        val pasanganText = "${bucin.pasangan1} & ${bucin.pasangan2}"
        holder.binding.tvPasangan.text = pasanganText

        // Mengambil emoji dari string tingkatAnomali (cth: "Bucin 😍" -> "😍")
        val emoji = bucin.tingkatAnomali.split(" ").lastOrNull() ?: "😐"
        holder.binding.tvEmoji.text = emoji

        holder.binding.root.setOnClickListener {
            onItemClick(bucin)
        }
    }


}