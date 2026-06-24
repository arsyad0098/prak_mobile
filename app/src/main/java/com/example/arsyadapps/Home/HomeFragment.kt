package com.example.arsyadapps.Home

// Pastikan semua import ini ada
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arsyadapps.AuthActivity
import com.example.arsyadapps.Data.AppDatabase
import com.example.arsyadapps.Data.entity.NoteEntity
import com.example.arsyadapps.Note.NoteAdapter
import com.example.arsyadapps.Note.NoteFormActivity
import com.example.arsyadapps.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    /** Variabel database & Adapter **/
    private lateinit var adapter: NoteAdapter
    private lateinit var db: AppDatabase
    private val notes = mutableListOf<NoteEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        /** Inisialisasi AppDatabase & Adapter **/
        db = AppDatabase.getInstance(requireContext())

        // SUDAH DIUPDATE: Ditambahkan parameter "this" agar bisa mendengarkan aksi hapus
        adapter = NoteAdapter(notes, this)

        // Setup RecyclerView rvNotes
        binding.rvNotes.layoutManager = LinearLayoutManager(requireContext())
        binding.rvNotes.adapter = adapter

        // Garis pemisah antar item
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.rvNotes.addItemDecoration(dividerItemDecoration)

        // Mematikan nested scrolling karena berada di dalam NestedScrollView
        binding.rvNotes.isNestedScrollingEnabled = false

        // Fetch data pertama kali
        fetchNotes()

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        // Aksi FAB untuk menambah catatan
        binding.fabAddNote.setOnClickListener {
            startActivity(Intent(requireContext(), NoteFormActivity::class.java))
        }

        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan2.SecondActivity::class.java))
        }

        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan3.ThirdActivity::class.java))
        }

        binding.btnPertemuan4.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan4.FourthActivity::class.java))
        }

        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan5.FifthActivity::class.java))
        }

        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan7.SeventhActivity::class.java))
        }

        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan_9.NinthActivity::class.java))
        }

        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan10.TenthActivity::class.java))
        }

        binding.btnPertemuan13.setOnClickListener {
            startActivity(Intent(requireContext(), com.example.arsyadapps.Home.pertemuan_13.ThirteenthActivity::class.java))
        }

        // ... (Kode tombol Pertemuan 2 - 10 Anda yang lain tetap aman di sini) ...

        // Fitur Logout
        binding.btnLogout.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin keluar?")
                .setPositiveButton("Ya") { _, _ ->
                    sharedPref.edit().clear().apply()
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                    requireActivity().finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }

    /** Mengambil data dari database menggunakan Coroutine **/
    private fun fetchNotes() {
        lifecycleScope.launch {
            val data = db.noteDao().getAll() // pemanggilan query
            notes.clear()
            notes.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    /** FUNGSI BARU: Menghapus catatan dari database dan refresh UI **/
    fun deleteNote(note: NoteEntity) {
        lifecycleScope.launch {
            db.noteDao().delete(note) // Hapus Note di database
            fetchNotes()              // Ambil ulang data notes terbaru untuk update UI
        }
    }

    /** Dipanggil saat Fragment kembali tampil setelah form ditutup **/
    override fun onResume() {
        super.onResume()
        fetchNotes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}