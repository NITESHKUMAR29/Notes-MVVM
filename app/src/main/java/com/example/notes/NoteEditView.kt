package com.example.notes

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_note_editview.*

class NoteEditView : AppCompatActivity() {
    private lateinit var viewModel: NoteViewModel
    private var noteId =0 // initial value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editview)

        // fetching values form intent

        val id = intent.extras?.get("note id")
        noteId = id as Int
        val text = intent.extras?.get("note text")
        val title = intent.extras?.get("note title")

        edTxt_Title_edit.setText(title as CharSequence)
        edTxt_note_edit.setText(text as CharSequence)

        viewModel= ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(NoteViewModel(application)::class.java)

    }

    override fun onBackPressed() {
        viewModel.updateNote(Note(edTxt_Title_edit.text.toString(),edTxt_note_edit.text.toString(),noteId))
        super.onBackPressed()
    }
}