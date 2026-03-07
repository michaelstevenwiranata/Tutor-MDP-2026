package com.example.minggu3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.minggu3.databinding.ItemStudentBinding

/**
 * StudentAdapter — Menggunakan ListAdapter + DiffUtil.
 *
 * ListAdapter adalah subclass dari RecyclerView.Adapter yang sudah built-in
 * support untuk menghitung perbedaan (diff) antara list lama dan list baru
 * secara otomatis di background thread menggunakan DiffUtil.
 *
 * Keuntungan dibanding RecyclerView.Adapter biasa:
 *   - Tidak perlu manual notifyDataSetChanged() (yang me-refresh SELURUH list)
 *   - Hanya item yang berubah yang di-update → lebih efisien & ada animasi
 *   - Tidak perlu menyimpan list sendiri (ListAdapter menyimpannya internal)
 *   - Cukup panggil submitList(newList) untuk memperbarui data
 */
class StudentAdapter(
    private val onItemClick: (Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    /**
     * DiffUtil.ItemCallback — Digunakan oleh ListAdapter untuk menentukan
     * apakah dua item sama atau berbeda.
     *
     * areItemsTheSame  → Cek apakah item yang sama (biasanya by ID/unique key).
     *                     Digunakan untuk mendeteksi apakah item dipindah/dihapus.
     * areContentsTheSame → Cek apakah isi/konten item berubah.
     *                      Digunakan untuk mendeteksi apakah item perlu di-rebind.
     */
    class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            // NRP adalah unique identifier untuk setiap mahasiswa
            return oldItem.nrp == newItem.nrp
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            // data class otomatis men-generate equals() berdasarkan semua properties
            return oldItem == newItem
        }
    }

    /**
     * ViewHolder — Menyimpan referensi ke View untuk satu item.
     * Menggunakan Data Binding (ItemStudentBinding).
     */
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
        // getItem() disediakan oleh ListAdapter — mengambil item dari internal list
        holder.bind(getItem(position))
    }

    // NOTE: Tidak perlu override getItemCount() — ListAdapter sudah handle otomatis.
    // NOTE: Tidak perlu fungsi updateList() — cukup panggil submitList(newList) dari luar.
}

