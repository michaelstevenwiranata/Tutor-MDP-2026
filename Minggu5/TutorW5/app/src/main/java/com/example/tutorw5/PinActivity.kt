package com.example.tutorw5

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tutorw5.databinding.ActivityPinBinding


class PinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPinBinding
    private val viewModel: PinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        // Observasi perubahan PIN
        viewModel.pinInput.observe(this) { currentPin ->
            // Mengubah tampilan text menjadi bintang/bullet menyesuaikan panjang pin
            val displayTxt = currentPin.padEnd(4, '-').replace(Regex("[0-9]"), "*")
            binding.tvPinDisplay.text = displayTxt
        }

        // Observasi validasi sukses
        viewModel.isPinValid.observe(this) { isValid ->
            if (isValid) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }

        // Observasi pesan error
        viewModel.errorMessage.observe(this) { errorMsg ->
            binding.tvError.text = errorMsg
            binding.tvError.visibility = View.VISIBLE
        }
    }

    private fun setupListeners() {
        // Event trigger diteruskan murni ke view model
        val buttons = listOf(
            binding.btn0, binding.btn1, binding.btn2, binding.btn3, binding.btn4,
            binding.btn5, binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                binding.tvError.visibility = View.GONE
                viewModel.onKeypadClicked(button.text.toString())
            }
        }
    }
}