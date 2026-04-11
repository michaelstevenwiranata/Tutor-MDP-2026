package com.example.tutorw5


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tutorw5.Element
import com.example.tutorw5.MockDB
import com.example.tutorw5.Reaction

class MainViewModel : ViewModel() {


    // Elements State
    private val _elements = MutableLiveData<List<Element>>()
    val elements: LiveData<List<Element>> = _elements

    // Reactions State
    private val _reactions = MutableLiveData<List<Reaction>>()
    val reactions: LiveData<List<Reaction>> = _reactions

    init {
        loadData()
    }

    private fun loadData() {
        _elements.value = MockDB.elements
        _reactions.value = MockDB.reactions.toList()
    }

    fun addReaction(name: String, element1: Element, element2: Element, description: String) {
        val newId = (MockDB.reactions.maxOfOrNull { it.id } ?: 0) + 1
        MockDB.reactions.add(Reaction(newId, name, element1, element2, description))
        _reactions.value = MockDB.reactions.toList()
    }

    fun editReaction(id: Int, newName: String, newElement1: Element, newElement2: Element, newDesc: String) {
        val index = MockDB.reactions.indexOfFirst { it.id == id }
        if (index != -1) {
            MockDB.reactions[index] = Reaction(id, newName, newElement1, newElement2, newDesc)
            _reactions.value = MockDB.reactions.toList()
        }
    }

    fun deleteReaction(id: Int) {
        MockDB.reactions.removeIf { it.id == id }
        _reactions.value = MockDB.reactions.toList()
    }
}