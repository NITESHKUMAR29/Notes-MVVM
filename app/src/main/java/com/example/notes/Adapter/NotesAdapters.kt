package com.example.notes.Adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Note
import com.example.notes.NoteViewModel
import com.example.notes.R
import com.example.notes.noteEditview
import java.nio.file.Files.delete

class NotesAdapters (private  val list: ArrayList<Note>,val  context: Context, val application: Application) : RecyclerView.Adapter<NotesAdapters.HomeActivity>() {
    lateinit var noteViewModel: NoteViewModel
    inner class HomeActivity(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemString: TextView = itemView.findViewById(R.id.mitem)
        val item2String: TextView = itemView.findViewById(R.id.m2item)
        val mselectImage: ImageView = itemView.findViewById(R.id.mselect)
        val card : ConstraintLayout = itemView.findViewById(R.id.card)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeActivity =
        HomeActivity(
            LayoutInflater.from(parent.context).inflate(R.layout.sample_notes, parent, false)
        )


    override fun getItemCount(): Int = list.size


    override fun onBindViewHolder(holder: HomeActivity, position: Int) {
        val currentItem = list[position]
        holder.itemString.text = currentItem.text
        holder.item2String.text=currentItem.subtext
        holder.mselectImage.setOnClickListener {
            NoteViewModel(application).delete(currentItem)
        }
        holder.card.setOnClickListener {
            val intent = Intent(context, noteEditview::class.java)
            intent.putExtra("note id",list[position].id)
            intent.putExtra("note text",list[position].subtext)
            intent.putExtra("note title",list[position].text)
            context.startActivity(intent)
        }
    }
    fun updateList(newList: List<Note>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()

    }
}

