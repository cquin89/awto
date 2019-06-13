package com.losingtimeapps.testawto.presentation.ui

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.losingtimeapps.testawto.R
import com.losingtimeapps.testawto.app.App
import com.losingtimeapps.testawto.di.modules.ActivityModule
import com.losingtimeapps.testawto.presentation.ui.vehicledescription.VehicleDescriptionFragment
import com.losingtimeapps.testawto.presentation.ui.vehiclelist.VehicleListFragment
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import android.content.Intent

class MainActivity : AppCompatActivity(), VehicleListFragment.OnFragmentInteractionListener
    , VehicleDescriptionFragment.OnFragmentInteractionListener {


    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()
    }

    fun initComponent() {
        val app = application as App
        val activityModule = ActivityModule(this)
        app.getComponent().createMainActivityComponent(activityModule).inject(this)
    }

    override fun getMainViewModel(): MainViewModel {
        return viewModel
    }

    override fun progressBarVisibility(visible: Boolean) {
        ProgressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (fragment in supportFragmentManager.fragments) {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

}
