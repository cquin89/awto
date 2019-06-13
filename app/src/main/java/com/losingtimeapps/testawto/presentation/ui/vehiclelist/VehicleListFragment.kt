package com.losingtimeapps.testawto.presentation.ui.vehiclelist

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.losingtimeapps.testawto.app.App
import com.losingtimeapps.testawto.domain.entity.Vehicle
import com.losingtimeapps.testawto.presentation.ui.MainViewModel
import com.losingtimeapps.testawto.presentation.utils.GenericAdapter
import kotlinx.android.synthetic.main.fragment_vehicle_list.*
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService
import com.losingtimeapps.testawto.R
import android.Manifest.permission
import android.Manifest.permission.RECORD_AUDIO
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.losingtimeapps.testawto.presentation.ui.MainActivity


class VehicleListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vehicle_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        solicPermission()

    }

    @SuppressLint("MissingPermission")
    fun initView() {
        val viewModel = listener!!.getMainViewModel()
        val lm = getSystemService(App.INSTANCE, LocationManager::class.java)
        val location = lm!!.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val longitude = location.longitude
        val latitude = location.latitude
        srlContainer.setOnRefreshListener {
            viewModel.getVehicle(latitude, longitude)
        }

        rvVehicles.layoutManager = LinearLayoutManager(context)
        rvVehicles.setHasFixedSize(true)

        val adapter = initAdapter()
        rvVehicles.adapter = adapter

        viewModel.onGetVehicleObservable.observe(this, Observer {
            srlContainer.isRefreshing = false
            adapter.listItems = it
            adapter.notifyDataSetChanged()
            listener?.progressBarVisibility(false)
        })
        viewModel.onErrorObservable.observe(this, Observer {
            showError(getString(it))
            listener?.progressBarVisibility(false)
        })

        if (viewModel.firstTime) {
            viewModel.firstTime = false
            viewModel.getVehicle(latitude, longitude)
            listener?.progressBarVisibility(true)
        }
    }


    private fun showError(errorMessage: String) {
        Toast.makeText(App.INSTANCE, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 666)
            solicPermission()

    }


    private fun initAdapter(): GenericAdapter<Vehicle> {
        return object : GenericAdapter<Vehicle>(), VehicleViewHolder.OnclickListener {
            override fun onClick(data: Vehicle, view: View) {

            }


            override fun getLayoutId(position: Int, obj: Vehicle): Int {
                return R.layout.item_vehicle
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return VehicleViewHolder(view, this)
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun solicPermission() {
        if (ContextCompat.checkSelfPermission(
                App.INSTANCE,
                permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {

            ActivityCompat.requestPermissions(
                activity as MainActivity,
                arrayOf(permission.ACCESS_FINE_LOCATION),
                666
            )

        } else {
            initView()
        }

    }

    interface OnFragmentInteractionListener {

        fun getMainViewModel(): MainViewModel
        fun progressBarVisibility(visible: Boolean)
    }

}
