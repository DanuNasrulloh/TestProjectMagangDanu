package model

import com.google.gson.annotations.SerializedName

class Riwayat (
    @SerializedName("tanggal_pelaporan") val tanggal: String,
    @SerializedName("tipe_kerusakan") val tipe: String,
    @SerializedName("deskripsi") val deskripsi: String,
    @SerializedName("bukti_foto") val fotoUrl: String,
    @SerializedName("tanggapan_petugas") val tanggapan: String
)