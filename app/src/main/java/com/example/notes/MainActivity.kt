package com.example.notes

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Adapter.NotesAdapters
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit  var list:ArrayList<Note>
    lateinit var adapters: NotesAdapters
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        list = ArrayList()

        adapters = NotesAdapters(list,this,application)
        recyclerView.adapter = adapters
        recyclerView.hasFixedSize()

        recyclerView.layoutManager=LinearLayoutManager(this)
        viewModel=ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(NoteViewModel(application)::class.java)
        viewModel.allNotes.observe(this, Observer {list->
            list?.let {
                adapters.updateList(it)
            }
        })

        submit.setOnClickListener {

            val note =input.text.toString()
            val notes=minput.text.toString()
            if(note.isNotEmpty()) {
                viewModel.insert(Note(note,notes))
                input.setText("")
                minput.setText("")
            }
//            if (notes.isNotEmpty()){
//                viewModel.insert(Note(notes))
//                minput.setText("")
//            }
        }

        delete_all.setOnClickListener {
            viewModel.deleteAll()
            Snackbar.make(it, "All notes deleted!", Snackbar.LENGTH_SHORT).show()
        }

    }

}