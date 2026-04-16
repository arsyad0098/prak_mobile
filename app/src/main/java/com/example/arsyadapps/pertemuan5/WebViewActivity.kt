package com.example.arsyadapps.pertemuan5

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.arsyadapps.R
import com.example.arsyadapps.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Inisialisasi View Binding
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        // 2. Setup Window Insets
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 3. Mengaktifkan Toolbar (Action Bar)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Web Merdeka"
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        // 4. Konfigurasi WebView
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://merdeka.com")

            // 5. Agar Toolbar hide/show saat scroll web
            setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (scrollY > oldScrollY) {
                    // Jika scroll ke bawah, sembunyikan AppBar
                    binding.appBar.setExpanded(false, true)
                } else if (scrollY < oldScrollY) {
                    // Jika scroll ke atas, tampilkan AppBar kembali
                    binding.appBar.setExpanded(true, true)
                }
            }
        }
    }

    // 6. Mengaktifkan navigasi kembali (Back Button)
    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack() // Kembali ke halaman web sebelumnya
        } else {
            super.onBackPressed() // Keluar dari Activity
        }
    }

    // Menangani klik tombol back pada Toolbar (tanda panah kiri)
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}