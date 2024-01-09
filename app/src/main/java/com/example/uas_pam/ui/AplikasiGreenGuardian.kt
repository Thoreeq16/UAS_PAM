package com.example.uas_pam.ui

import android.app.Application
import com.example.uas_pam.repositori.ContainerApp
import com.example.uas_pam.repositori.ContainerDataApp

class AplikasiGreenGuardian : Application() {
    lateinit var container: ContainerApp

    override fun onCreate() {
        super.onCreate()
        container = ContainerDataApp(this)
    }
}