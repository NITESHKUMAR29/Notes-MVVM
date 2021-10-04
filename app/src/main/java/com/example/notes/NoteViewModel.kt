package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){
    private val repository:NoteRepository
    val allNotes: LiveData<List<Note>>

    init{
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
       repository=NoteRepository(dao)
        allNotes=repository.allNotes
    }
    fun delete(note: Note)=viewModelScope.launch(Dispatchers.IO){
        repository.delete(note)
    }

    fun insert(note: Note)=viewModelScope.launch  (Dispatchers.IO) {
        repository.insert(note)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note)
    }


}