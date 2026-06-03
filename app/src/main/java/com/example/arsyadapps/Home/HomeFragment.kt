package com.example.arsyadapps.Home

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.arsyadapps.AuthActivity
import com.example.arsyadapps.Data.Api.PhotoApiClient
import com.example.arsyadapps.Home.pertemuan10.TenthActivity
import com.example.arsyadapps.Home.pertemuan2.SecondActivity
import com.example.arsyadapps.Home.pertemuan3.ThirdActivity
import com.example.arsyadapps.Home.pertemuan4.FourthActivity
import com.example.arsyadapps.Home.pertemuan5.FifthActivity
import com.example.arsyadapps.Home.pertemuan7.SeventhActivity
import com.example.arsyadapps.Home.pertemuan_9.NinthActivity
import com.example.arsyadapps.Home.photo.PhotoAdapter
import com.example.arsyadapps.R
import com.example.arsyadapps.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Home"
        }

        val sharedPref = requireContext().getSharedPreferences("user_pref", Context.MODE_PRIVATE)

        binding.btnPertemuan2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        binding.btnPertemuan3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        binding.btnPertemuan4.setOnClickListener {
            startActivity(Intent(requireContext(), FourthActivity::class.java))
        }

        binding.btnPertemuan5.setOnClickListener {
            startActivity(Intent(requireContext(), FifthActivity::class.java))
        }

        binding.btnPertemuan7.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        binding.btnPertemuan9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        binding.btnPertemuan10.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }

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

        binding.btnRefresh.setOnClickListener {
            loadPhoto()
        }

        loadPhoto()
    }

    private fun loadPhoto() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                // Memberikan feedback ke logcat untuk debugging
                val photos = PhotoApiClient.apiService.getPhotos()
                
                if (photos.isNotEmpty()) {
                    val adapter = PhotoAdapter(photos)
                    val currentContext = context ?: return@launch

                    binding.rvGallery.adapter = adapter
                    binding.rvGallery.layoutManager = LinearLayoutManager(currentContext)
                    // Nonaktifkan nested scrolling pada RV karena sudah di dalam NestedScrollView
                    binding.rvGallery.isNestedScrollingEnabled = false
                } else {
                    Toast.makeText(context, "Data gambar kosong", Toast.LENGTH_SHORT).show()
                }

            } catch (e: Exception) {
                // Menampilkan error yang lebih spesifik agar mudah diperbaiki
                val errorMessage = when (e) {
                    is java.net.UnknownHostException -> "Tidak ada koneksi internet"
                    is retrofit2.HttpException -> "Server error: ${e.code()}"
                    else -> "Gagal memuat gambar: ${e.localizedMessage}"
                }
                context?.let {
                    Toast.makeText(it, errorMessage, Toast.LENGTH_LONG).show()
                }
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
