package Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.button.MaterialButton
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectmagang.DetailPelangganActivity
import com.example.testprojectmagang.R
import model.Pelanggan

class PelangganAdapter(
    private val listPelanggan: List<Pelanggan>,
    private val onCatatClick: (Pelanggan) -> Unit
) : RecyclerView.Adapter<PelangganAdapter.PelangganViewHolder>() {

    inner class PelangganViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvId: TextView = itemView.findViewById(R.id.tvIdPelanggan)
        val tvNama: TextView = itemView.findViewById(R.id.tvNama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
        val btnCatat: MaterialButton = itemView.findViewById(R.id.btnCatat)
        val ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PelangganViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_recycler_riwayat_pelanggan_belum_dicatat, parent, false)
        return PelangganViewHolder(view)
    }

    override fun onBindViewHolder(holder: PelangganViewHolder, position: Int) {
        val pelanggan = listPelanggan[position]
        holder.tvId.text = "ID - ${pelanggan.id}"
        holder.tvNama.text = pelanggan.nama
        holder.tvAlamat.text = pelanggan.alamat

        holder.btnCatat.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailPelangganActivity::class.java)
            intent.putExtra("data_pelanggan", pelanggan)
            context.startActivity(intent)
        }


        if (position % 2 == 0) {
            holder.ivStatus.setImageResource(R.drawable.ic_star_outline)
        } else {
            holder.ivStatus.setImageResource(R.drawable.ic_star_outline)
        }
    }

    override fun getItemCount(): Int = listPelanggan.size
}