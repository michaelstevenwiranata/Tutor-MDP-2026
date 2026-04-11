package com.example.tutorw5


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutorw5.MockDB

class PinViewModel : ViewModel() {

    private val _pinInput = MutableLiveData<String>("")
    val pinInput: LiveData<String> = _pinInput

    private val _isPinValid = MutableLiveData<Boolean>()
    val isPinValid: LiveData<Boolean> = _isPinValid

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun onKeypadClicked(digit: String) {
        val currentPin = _pinInput.value ?: ""
        if (currentPin.length < 4) {
            val newPin = currentPin + digit
            _pinInput.value = newPin

            // Evaluasi jika sudah 4 digit
            if (newPin.length == 4) {
                validatePin(newPin)
            }
        }
    }

    private fun validatePin(pin: String) {
        if (pin == MockDB.CURRENT_USER_PIN) {
            _isPinValid.value = true
        } else {
            _errorMessage.value = "PIN Salah! Paimon marah!"
            _pinInput.value = "" // Reset PIN
        }
    }
}