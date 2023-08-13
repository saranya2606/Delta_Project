package com.example.maps
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val results: List<Place>
)
data class LodgingDetails(
    val name: String,
    val reviews: List<String>
)







data class Place(
    val name: String,
    val geometry: Geometry
) /*: Parcelable {
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




data class Geometry(
    @SerializedName("location") val location: Location
) /*: Parcelable {
    // Parcelable constructor

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        Location(parcel.readDouble(), parcel.readDouble())
    )

    // Write object's data to the parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(location, flags)
    }

    // Parcelable creator
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Geometry> {
        override fun createFromParcel(parcel: Parcel): Geometry {
            return Geometry(parcel)
        }

        override fun newArray(size: Int): Array<Geometry?> {
            return arrayOfNulls(size)
        }
    }
}*/


data class Location(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lng") val lng: Double
) /*: Parcelable {
    // Parcelable constructor
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readDouble()
    )

    // Write object's data to the parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(lat)
        parcel.writeDouble(lng)
    }

    // Parcelable creator
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}*/
data class GeoResponse(
    val results: List<GeoResult>
)

data class GeoResult(
    val geometry: GeoGeometry
)

data class GeoGeometry(
    val location: GeoLocation
)

data class GeoLocation(
    val lat: Double,
    val lng: Double
)


