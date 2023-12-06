package com.example.roomdatabase

import android.app.Application
import com.example.roomdatabase.Repositori.ContainerApp
import com.example.roomdatabase.Repositori.ContainerDataApp

class AplikasiSiswa : Application(){
    lateinit var container : ContainerApp

    override fun onCreate(){
        super.onCreate()
        container = ContainerDataApp(this)
    }
}