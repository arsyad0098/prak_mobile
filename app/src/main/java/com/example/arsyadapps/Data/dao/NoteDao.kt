package com.example.arsyadapps.Data.dao

import androidx.room.Dao
import androidx.room.Delete // Import baru
import androidx.room.Insert
import androidx.room.Query
import com.example.arsyadapps.Data.entity.NoteEntity

@Dao
interface NoteDao {
    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<NoteEntity>

    @Insert
    suspend fun insert(note: NoteEntity)

    @Delete // Aksi hapus baru
    suspend fun delete(note: NoteEntity)
}