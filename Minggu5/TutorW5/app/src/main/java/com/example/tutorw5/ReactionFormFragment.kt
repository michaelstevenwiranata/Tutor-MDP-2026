package com.example.tutorw5
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tutorw5.databinding.FragmentReactionFormBinding

class ReactionFormFragment : Fragment() {

    private var _binding: FragmentReactionFormBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private var reactionId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReactionFormBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengambil argumen ID jika ada (default -1 berarti Add Mode)
        reactionId = arguments?.getInt("reactionId", -1) ?: -1

        setupUI()
        setupListeners()
    }

    private fun setupUI() {
        val elementsList = viewModel.elements.value ?: emptyList()
        val elementNames = elementsList.map { it.name }

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            elementNames
        )

        binding.spinnerElement1.adapter = spinnerAdapter
        binding.spinnerElement2.adapter = spinnerAdapter

        if (reactionId != -1) {
            binding.tvFormTitle.text = "Ubah Reaction"
            // Mode Edit: Ambil data dari list reaction berdasarkan ID
            val reaction = viewModel.reactions.value?.find { it.id == reactionId }
            reaction?.let {
                binding.etReactionName.setText(it.name)
                binding.etReactionDesc.setText(it.description)

                val index1 = elementsList.indexOfFirst { el -> el.id == it.element1.id }
                val index2 = elementsList.indexOfFirst { el -> el.id == it.element2.id }

                if (index1 >= 0) binding.spinnerElement1.setSelection(index1)
                if (index2 >= 0) binding.spinnerElement2.setSelection(index2)
            }
        } else {
            binding.tvFormTitle.text = "Tambah Reaction"
        }
    }

    private fun setupListeners() {
        binding.btnCancel.setOnClickListener {
            // Kembali ke halaman sebelumnya
            findNavController().popBackStack()
            findNavController().navigate(R.id.action_global_nav_reaction)

        }

        binding.btnSave.setOnClickListener {
            val name = binding.etReactionName.text.toString()
            val desc = binding.etReactionDesc.text.toString()

            val elementsList = viewModel.elements.value ?: return@setOnClickListener
            val selectedElement1 = elementsList[binding.spinnerElement1.selectedItemPosition]
            val selectedElement2 = elementsList[binding.spinnerElement2.selectedItemPosition]

            if (reactionId == -1) {
                // Mode Add
                viewModel.addReaction(name, selectedElement1, selectedElement2, desc)
            } else {
                // Mode Edit
                viewModel.editReaction(reactionId, name, selectedElement1, selectedElement2, desc)
            }

            // Setelah save di ViewModel, tutup halaman form
            findNavController().popBackStack()
            findNavController().navigate(R.id.action_global_nav_reaction)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}