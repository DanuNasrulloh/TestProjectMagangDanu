package com.example.testprojectmagang

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.io.IOException

class ProsesLaporan : AppCompatActivity() {

    private lateinit var etKomentar: TextInputEditText
    private lateinit var imgSebelum1: ImageView
    private lateinit var imgSesudah1: ImageView
    private lateinit var btnKirim: Button

    private val PICK_IMAGE_BEFORE = 100
    private val PICK_IMAGE_AFTER = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_proses_laporan) // XML yang kamu kasih

        // Inisialisasi View
        etKomentar = findViewById(R.id.etKomentar)
        imgSebelum1 = findViewById(R.id.imgSebelum1)
        imgSesudah1 = findViewById(R.id.imgSesudah1)
        btnKirim = findViewById(R.id.btnKirim)

        val frameAddSebelum = findViewById<FrameLayout>(R.id.frameAddSebelum)
        val frameAddSesudah = findViewById<FrameLayout>(R.id.frameAddSesudah)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        // Back
        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Tambah foto Sebelum
        frameAddSebelum.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_BEFORE)
        }

        // Tambah foto Sesudah
        frameAddSesudah.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE_AFTER)
        }

        // Tombol Kirim
        btnKirim.setOnClickListener {
            val komentar = etKomentar.text.toString()

            if (komentar.isEmpty()) {
                Toast.makeText(this, "Komentar belum diisi", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Laporan berhasil dikirim!", Toast.LENGTH_LONG).show()
                finish() // balik ke halaman sebelumnya
            }
        }
    }

    // Ambil gambar dari gallery
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data
            try {
                val bitmap: Bitmap =
                    MediaStore.Images.Media.getBitmap(this.contentResolver, selectedImageUri)

                when (requestCode) {
                    PICK_IMAGE_BEFORE -> imgSebelum1.setImageBitmap(bitmap)
                    PICK_IMAGE_AFTER -> imgSesudah1.setImageBitmap(bitmap)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}