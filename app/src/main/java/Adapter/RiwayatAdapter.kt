package Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testprojectmagang.DetailLaporanRiwayat
import com.example.testprojectmagang.R
import model.Riwayat

class RiwayatAdapter(private val listRiwayat: List<Riwayat>) :
    RecyclerView.Adapter<RiwayatAdapter.RiwayatViewHolder>() {

    class RiwayatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTanggal: TextView = itemView.findViewById(R.id.tvTanggal)
        val tvTipe: TextView = itemView.findViewById(R.id.tvTipe)
        val tvDeskripsi: TextView = itemView.findViewById(R.id.tvDeskripsi)
        val imgBukti: ImageView = itemView.findViewById(R.id.imgBukti)
        val tvTanggapan: TextView = itemView.findViewById(R.id.tvTanggapan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RiwayatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_riwayat, parent, false)
        return RiwayatViewHolder(view)
    }

    override fun onBindViewHolder(holder: RiwayatViewHolder, position: Int) {
        val item = listRiwayat[position]
        val context = holder.itemView.context

        holder.tvTanggal.text = item.tanggal
        holder.tvTipe.text = item.tipe
        holder.tvDeskripsi.text = item.deskripsi
        holder.tvTanggapan.text = "Tanggapan: ${item.tanggapan}"

        // Cek apakah fotoUrl berupa link (http/https) atau nama file offline
        if (item.fotoUrl.startsWith("http")) {
            // Load gambar dari internet pakai Glide
            Glide.with(context)
                .load(item.fotoUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imgBukti)
        } else {
            // Load gambar dari drawable (offline)
            val resId = context.resources.getIdentifier(
                item.fotoUrl.substringBeforeLast("."), // hilangkan .jpg
                "drawable",
                context.packageName
            )

            if (resId != 0) {
                holder.imgBukti.setImageResource(resId)
            } else {
                holder.imgBukti.setImageResource(R.drawable.ic_launcher_foreground)
            }
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailLaporanRiwayat::class.java)
            intent.putExtra("tanggal", item.tanggal)
            intent.putExtra("tipe", item.tipe)
            intent.putExtra("deskripsi", item.deskripsi)
            intent.putExtra("foto", item.fotoUrl)
            intent.putExtra("tanggapan", item.tanggapan)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = listRiwayat.size
}