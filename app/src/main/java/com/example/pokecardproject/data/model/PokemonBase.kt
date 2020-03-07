package com.example.pokecardproject.data.model
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class PokemonBase(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("urlBackImg") val urlBackImg: String?,
    @SerializedName("urlFrontImg") val urlFrontImg: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
        parcel.writeString(urlBackImg)
        parcel.writeString(urlFrontImg)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonBase> {
        override fun createFromParcel(parcel: Parcel): PokemonBase {
            return PokemonBase(parcel)
        }

        override fun newArray(size: Int): Array<PokemonBase?> {
            return arrayOfNulls(size)
        }
    }
}