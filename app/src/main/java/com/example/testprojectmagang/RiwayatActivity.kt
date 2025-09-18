package com.example.testprojectmagang

import Adapter.RiwayatAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.utils.JsonHelper

class RiwayatActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var riwayatAdapter: RiwayatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_riwayat)

        recyclerView = findViewById(R.id.rvRiwayat)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Ambil data JSON dari assets
        val dataRiwayat = JsonHelper.loadRiwayatFromAssets(this)

        // Set adapter
        riwayatAdapter = RiwayatAdapter(dataRiwayat)
        recyclerView.adapter = riwayatAdapter

        // === BOTTOM NAV BAR ===
        val navHome = findViewById<LinearLayout>(R.id.navHome)
        val navListPelanggan = findViewById<LinearLayout>(R.id.navListPelanggan)
        val navRiwayat = findViewById<LinearLayout>(R.id.navRiwayat)
        val navProfile = findViewById<LinearLayout>(R.id.navProfile)

        navHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        navListPelanggan.setOnClickListener {
            startActivity(Intent(this, ListPelanggan::class.java))
        }

        navRiwayat.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }
}
