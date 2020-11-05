package com.hmmanit.android.cleanweather.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import android.os.Build


object ConnectionManager {

    var isConnected = false

    fun checkNetworkConnection(context: Context) {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
//            isConnected = activeNetwork?.isConnectedOrConnecting == true
//        } else {
            registerNetworkCallback(context)
//        }
    }


    private fun registerNetworkCallback(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        cm.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                isConnected = true
            }

            override fun onLost(network: Network) {
                isConnected = false
            }
        })
        return isConnected
    }
}