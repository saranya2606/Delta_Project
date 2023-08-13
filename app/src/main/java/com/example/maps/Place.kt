package com.example.maps

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

/*data class Place(
    val name: String,
    val geometry: Geometry
) : Parcelable {
    // Implement the Parcelable interface methods here

    // Parcelable constructor
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readParcelable(Geometry::class.java.classLoader) ?: Geometry()
    )

    // Write object's data to the parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(geometry, flags)
    }

    // Parcelable creator
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Place> {
        override fun createFromParcel(parcel: Parcel): Place {
            return Place(parcel)
        }

        override fun newArray(size: Int): Array<Place?> {
            return arrayOfNulls(size)
        }
    }
}*/
