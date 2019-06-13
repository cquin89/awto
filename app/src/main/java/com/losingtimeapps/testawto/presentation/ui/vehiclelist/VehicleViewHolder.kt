package com.losingtimeapps.testawto.presentation.ui.vehiclelist

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.losingtimeapps.testawto.R
import com.losingtimeapps.testawto.app.App
import com.losingtimeapps.testawto.domain.entity.Vehicle
import com.losingtimeapps.testawto.presentation.utils.GenericAdapter

class VehicleViewHolder : RecyclerView.ViewHolder, GenericAdapter.Binder<Vehicle> {
    override fun bind(data: Vehicle) {
        tvType.text = data.type
        tvModel.text = String.format(App.INSTANCE.getString(R.string.model), data.model)
        tvFulltype.text = String.format(App.INSTANCE.getString(R.string.full_type), data.fuelType)
        tvTrasmision.text = String.format(App.INSTANCE.getString(R.string.transmision), data.transmission)
        tvLicense.text = String.format(App.INSTANCE.getString(R.string.license_plate), data.licensePlate)
        tvSeat.text = String.format(App.INSTANCE.getString(R.string.seat_count), data.seatCount.toString())
        tvDistance.text = String.format(App.INSTANCE.getString(R.string.distance), data.getDistance())
        tvRentalInUse.text =
            String.format(App.INSTANCE.getString(R.string.rental_in_use), data.rentalPriceInUse.toString())
        tvRentalInPark.text =
            String.format(App.INSTANCE.getString(R.string.rental_in_parking), data.rentalPriceInParking.toString())
        clContainer.setOnClickListener { listener.onClick(data, it) }
    }

    var tvType: TextView
    var tvModel: TextView
    var tvFulltype: TextView
    var tvTrasmision: TextView
    var tvLicense: TextView
    var tvSeat: TextView
    var tvDistance: TextView
    var tvRentalInUse: TextView
    var tvRentalInPark: TextView
    var clContainer: ConstraintLayout
    var listener: OnclickListener

    constructor(itemView: View, listener: OnclickListener) : super(itemView) {
        tvType = itemView.findViewById(R.id.tvType)
        tvModel = itemView.findViewById(R.id.tvModel)
        tvFulltype = itemView.findViewById(R.id.tvFulltype)
        tvTrasmision = itemView.findViewById(R.id.tvTransmision)
        tvLicense = itemView.findViewById(R.id.tvLicensePlate)
        tvSeat = itemView.findViewById(R.id.tvSeatCount)
        tvDistance = itemView.findViewById(R.id.tvDistance)
        tvRentalInPark = itemView.findViewById(R.id.tvRentalPriceInParking)
        tvRentalInUse = itemView.findViewById(R.id.tvRentalPriceInUse)

        clContainer = itemView.findViewById(R.id.clContainer)
        this.listener = listener

    }

    interface OnclickListener {
        fun onClick(data: Vehicle, view: View)
    }


}