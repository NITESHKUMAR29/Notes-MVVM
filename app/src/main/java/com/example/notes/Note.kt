package com.example.notes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "notes_table_2")
class Note(@ColumnInfo(name="text")val text:String, @ColumnInfo(name = "textInfo")val subtext:String) {
    @PrimaryKey(autoGenerate = true) var id=0
}