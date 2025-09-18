package model

import android.os.Parcel
import android.os.Parcelable

data class Pelanggan(
    val id: String,
    val nama: String,
    val alamat: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(nama)
        parcel.writeString(alamat)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Pelanggan> {
        override fun createFromParcel(parcel: Parcel): Pelanggan = Pelanggan(parcel)
        override fun newArray(size: Int): Array<Pelanggan?> = arrayOfNulls(size)
    }
}
