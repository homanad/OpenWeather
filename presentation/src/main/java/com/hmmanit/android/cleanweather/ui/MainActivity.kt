package com.hmmanit.android.cleanweather.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hmmanit.android.cleanweather.R
import com.hmmanit.android.cleanweather.common.ConnectionManager
import com.hmmanit.android.cleanweather.ui.vm.MainViewModel
import com.hmmanit.android.cleanweather.ui.vm.MainViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainViewModelFactory =
            MainViewModelFactory(this)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        observe()

        ConnectionManager.checkNetworkConnection(this)
        button.setOnClickListener {
            if (ConnectionManager.isConnected) {
                Log.d("aaaa", "data from remote")
                viewModel.getWeatherFromRemote("Ho Chi Minh City")
            } else {
                Log.d("aaaa", "data from local")
                viewModel.getWeatherFromLocal()
            }
        }
    }

    private fun observe() {
        viewModel.weatherResponse.observe(this, Observer {
            Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
        })
        viewModel.eventMessage.observe(this, Observer {
            Toast.makeText(this@MainActivity, it.getContentIfNotHandled(), Toast.LENGTH_SHORT)
                .show()
        })
    }
}
