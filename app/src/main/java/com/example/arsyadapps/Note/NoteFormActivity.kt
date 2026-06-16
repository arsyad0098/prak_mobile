package com.example.arsyadapps.Note

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.arsyadapps.Data.AppDatabase
import com.example.arsyadapps.Data.entity.NoteEntity // Sesuaikan dengan lokasi package NoteEntity Anda
import com.example.arsyadapps.databinding.ActivityNoteFormBinding
import kotlinx.coroutines.launch

class NoteFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteFormBinding
    private lateinit var db: AppDatabase // Menambahkan deklarasi database Room

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /** Toolbar Setup **/
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Note Form"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        /** Inisialisasi DB **/
        db = AppDatabase.getInstance(this)

        /** Event Handler tombol Save **/
        binding.btnSaveNote.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val content = binding.etContent.text.toString()

            // Validasi input agar tidak kosong
            if (title.isNotBlank() && content.isNotBlank()) {

                /** Penggunaan Coroutine untuk melakukan operasi database (IO Thread) **/
                lifecycleScope.launch {
                    val note = NoteEntity(
                        title = title,
                        content = content,
                        createdAt = System.currentTimeMillis()
                    )

                    // Memanggil fungsi insert dari DAO melalui AppDatabase
                    db.noteDao().insert(note)

                    // Memberikan informasi ke user dan menutup activity form
                    Toast.makeText(this@NoteFormActivity, "Catatan berhasil disimpan", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } else {
                // Notifikasi jika ada kolom yang belum diisi
                Toast.makeText(this, "Isi semua kolom!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}