package com.example.testprojectmagang

import Adapter.PelangganAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import model.Pelanggan
import java.io.IOException

class ListPelanggan : AppCompatActivity() {

    private lateinit var rvPelanggan: RecyclerView
    private lateinit var adapter: PelangganAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pelanggan)

        rvPelanggan = findViewById(R.id.rvPelanggan)

        // load JSON dari assets
        val dummyPelanggan = loadDummyData()

        adapter = PelangganAdapter(dummyPelanggan) { pelanggan ->
            Toast.makeText(this, "Catat: ${pelanggan.nama}", Toast.LENGTH_SHORT).show()
        }

        rvPelanggan.layoutManager = LinearLayoutManager(this)
        rvPelanggan.adapter = adapter

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

    private fun loadDummyData(): List<Pelanggan> {
        val jsonString: String
        try {
            jsonString = assets.open("pelanggan.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return emptyList()
        }

        val listType = object : TypeToken<List<Pelanggan>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }

}