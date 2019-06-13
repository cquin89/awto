package com.losingtimeapps.testawto.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Vehicle() : Parcelable {
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("zoneId")
    @Expose
    var zoneId: Int? = null
    @SerializedName("accuracy")
    @Expose
    var accuracy: Int? = null
    @SerializedName("address")
    @Expose
    var address: Any? = null
    @SerializedName("model")
    @Expose
    var model: String? = null
    @SerializedName("licensePlate")
    @Expose
    var licensePlate: String? = null
    @SerializedName("fuelType")
    @Expose
    var fuelType: String? = null
    @SerializedName("transmission")
    @Expose
    var transmission: String? = null
    @SerializedName("seatCount")
    @Expose
    var seatCount: Int? = null
    @SerializedName("baggageCount")
    @Expose
    var baggageCount: Int? = null
    @SerializedName("fuelLevel")
    @Expose
    var fuelLevel: String? = null
    @SerializedName("interiorRating")
    @Expose
    var interiorRating: Int? = null
    @SerializedName("fixedPrice")
    @Expose
    var fixedPrice: Int? = null
    @SerializedName("rentalPriceInUse")
    @Expose
    var rentalPriceInUse: Int? = null
    @SerializedName("rentalPriceInParking")
    @Expose
    var rentalPriceInParking: Int? = null
    @SerializedName("priceTimeUnit")
    @Expose
    var priceTimeUnit: Any? = null
    @SerializedName("priceCurrency")
    @Expose
    var priceCurrency: Any? = null
    @SerializedName("priceDescription")
    @Expose
    var priceDescription: Any? = null
    @SerializedName("nameCaption")
    @Expose
    var nameCaption: Any? = null
    @SerializedName("description")
    @Expose
    var description: Any? = null
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: Any? = null
    @SerializedName("lastUpdatedAt")
    @Expose
    var lastUpdatedAt: String? = null
    @SerializedName("vehicleStauts")
    @Expose
    var vehicleStauts: String? = null
    var distanceFromUser: Float? = 0f

    fun getDistance(): String {
        return if (distanceFromUser!! < 1.0) {
            (distanceFromUser!! * 1000).toLong().toString() + " M"

        } else
            String.format("%.2f", distanceFromUser) + " Km"

    }

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        type = parcel.readString()
        latitude = parcel.readValue(Double::class.java.classLoader) as? Double
        longitude = parcel.readValue(Double::class.java.classLoader) as? Double
        zoneId = parcel.readValue(Int::class.java.classLoader) as? Int
        accuracy = parcel.readValue(Int::class.java.classLoader) as? Int
        model = parcel.readString()
        licensePlate = parcel.readString()
        fuelType = parcel.readString()
        transmission = parcel.readString()
        seatCount = parcel.readValue(Int::class.java.classLoader) as? Int
        baggageCount = parcel.readValue(Int::class.java.classLoader) as? Int
        fuelLevel = parcel.readString()
        interiorRating = parcel.readValue(Int::class.java.classLoader) as? Int
        fixedPrice = parcel.readValue(Int::class.java.classLoader) as? Int
        rentalPriceInUse = parcel.readValue(Int::class.java.classLoader) as? Int
        rentalPriceInParking = parcel.readValue(Int::class.java.classLoader) as? Int
        lastUpdatedAt = parcel.readString()
        vehicleStauts = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(type)
        parcel.writeValue(latitude)
        parcel.writeValue(longitude)
        parcel.writeValue(zoneId)
        parcel.writeValue(accuracy)
        parcel.writeString(model)
        parcel.writeString(licensePlate)
        parcel.writeString(fuelType)
        parcel.writeString(transmission)
        parcel.writeValue(seatCount)
        parcel.writeValue(baggageCount)
        parcel.writeString(fuelLevel)
        parcel.writeValue(interiorRating)
        parcel.writeValue(fixedPrice)
        parcel.writeValue(rentalPriceInUse)
        parcel.writeValue(rentalPriceInParking)
        parcel.writeString(lastUpdatedAt)
        parcel.writeString(vehicleStauts)
    }


    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Vehicle> {
        override fun createFromParcel(parcel: Parcel): Vehicle {
            return Vehicle(parcel)
        }

        override fun newArray(size: Int): Array<Vehicle?> {
            return arrayOfNulls(size)
        }
    }

}