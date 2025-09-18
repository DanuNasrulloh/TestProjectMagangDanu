package com.example.testprojectmagang

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import model.Pelanggan
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DetailPelangganActivity : AppCompatActivity() {

    private lateinit var tvCustomerId: TextView
    private lateinit var tvNamaPelanggan: TextView
    private lateinit var tvAlamat: TextView
    private lateinit var tvTanggal: TextView
    private lateinit var tvJam: TextView
    private lateinit var ivFotoRumah: ImageView
    private lateinit var ivFotoMeter: ImageView
    private lateinit var etAngkaMeter: EditText
    private lateinit var btnSimpan: Button

    private var selectedPelanggan: Pelanggan? = null
    private val REQUEST_CAMERA_RUMAH = 100
    private val REQUEST_CAMERA_METER = 200
    private var currentTarget: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_pelanggan)

        // Binding
        tvCustomerId = findViewById(R.id.tvCustomerId)
        tvNamaPelanggan = findViewById(R.id.tvNamaPelanggan)
        tvAlamat = findViewById(R.id.tvAlamat)
        tvTanggal = findViewById(R.id.tvTanggal)
        tvJam = findViewById(R.id.tvJam)
        ivFotoRumah = findViewById(R.id.ivFotoRumah)
        ivFotoMeter = findViewById(R.id.ivFotoMeter)
        etAngkaMeter = findViewById(R.id.etAngkaMeter)
        btnSimpan = findViewById(R.id.btnSimpanData)

        // Ambil data pelanggan yang dilempar dari adapter
        selectedPelanggan = intent.getParcelableExtra("data_pelanggan")

        selectedPelanggan?.let { p ->
            tvCustomerId.text = "ID - ${p.id}"
            tvNamaPelanggan.text = p.nama
            tvAlamat.text = p.alamat
        }

        // Set tanggal & jam
        val now = Calendar.getInstance().time
        tvTanggal.text = SimpleDateFormat("EEEE, dd MMMM yyyy", Locale("id")).format(now)
        tvJam.text = SimpleDateFormat("HH:mm 'WIB'", Locale("id")).format(now)

        // Kamera Rumah
        ivFotoRumah.setOnClickListener {
            currentTarget = REQUEST_CAMERA_RUMAH
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CAMERA_RUMAH)
        }

        // Kamera Meter
        ivFotoMeter.setOnClickListener {
            currentTarget = REQUEST_CAMERA_METER
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, REQUEST_CAMERA_METER)
        }

        // Simpan Data
        btnSimpan.setOnClickListener {
            val angkaMeter = etAngkaMeter.text.toString()
            Toast.makeText(this, "Data tersimpan untuk ${selectedPelanggan?.nama} \nMeter: $angkaMeter", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val bitmap = data?.extras?.get("data") as? Bitmap
            bitmap?.let {
                val watermarked = addWatermark(it)
                when (requestCode) {
                    REQUEST_CAMERA_RUMAH -> ivFotoRumah.setImageBitmap(watermarked)
                    REQUEST_CAMERA_METER -> ivFotoMeter.setImageBitmap(watermarked)
                }
            }
        }
    }

    private fun addWatermark(original: Bitmap): Bitmap {
        val config = original.config ?: Bitmap.Config.ARGB_8888
        val result = Bitmap.createBitmap(original.width, original.height, config)
        val canvas = Canvas(result)
        canvas.drawBitmap(original, 0f, 0f, null)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.WHITE
            textSize = (original.width * 0.04f)
            style = Paint.Style.FILL
            setShadowLayer(2f, 1f, 1f, Color.BLACK)
        }

        val watermark = "PTAM - ${SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("id")).format(Date())}"
        val padding = original.width * 0.02f
        val textWidth = paint.measureText(watermark)
        val x = original.width - textWidth - padding
        val y = original.height - padding

        canvas.drawText(watermark, x, y, paint)
        return result
    }

}