package com.rawtooth.admin_waste

import android.app.Application
import com.easyvolley.NetworkClient

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NetworkClient.init(this)
    }
}