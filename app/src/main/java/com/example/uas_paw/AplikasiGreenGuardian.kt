package com.example.uas_paw

import android.app.Application
import com.example.uas_paw.repositori.ContainerApp
import com.example.uas_paw.repositori.ContainerDataApp

class AplikasiGreenGuardian : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}