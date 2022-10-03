package com.example.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("Select * from notes_table_2 order by id ASC")
    fun getAllNote(): LiveData<List<Note>>

    @Query("DELETE from notes_table_2")
    fun deleteAllNotes()

    @Update
    fun updateNote(note: Note)

}