package com.example.testprojectmagang

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailLaporanRiwayat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_laporan_riwayat) // xml detail yang kamu buat

        // Ambil data dari Intent
        val tanggal = intent.getStringExtra("tanggal")
        val tipe = intent.getStringExtra("tipe")
        val deskripsi = intent.getStringExtra("deskripsi")
        val foto = intent.getStringExtra("foto")
        val tanggapan = intent.getStringExtra("tanggapan")

        // Bind ke UI
        findViewById<TextView>(R.id.tvTanggal).text = tanggal
        findViewById<TextView>(R.id.tvTipe).text = tipe
        findViewById<TextView>(R.id.tvDeskripsi).text = deskripsi
        findViewById<TextView>(R.id.tvTanggapan).text = tanggapan

        val imgBukti1 = findViewById<ImageView>(R.id.imgBukti1)

        if (!foto.isNullOrEmpty()) {
            if (foto.startsWith("http")) {
                Glide.with(this).load(foto).into(imgBukti1)
            } else {
                val resId = resources.getIdentifier(
                    foto.substringBeforeLast("."), "drawable", packageName
                )
                if (resId != 0) imgBukti1.setImageResource(resId)
            }
        }

        // Tombol Back
        findViewById<ImageView>(R.id.btnBack).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        findViewById<Button>(R.id.btnProses).setOnClickListener {
            val intent = Intent(this, ProsesLaporan::class.java)
            startActivity(intent)
        }
    }
}