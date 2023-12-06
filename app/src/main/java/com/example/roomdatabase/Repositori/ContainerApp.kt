package com.example.roomdatabase.Repositori

import android.content.Context
import com.example.roomdatabase.Data.DatabaseSiswa

interface ContainerApp {
    val repositoriSiswa : RepositoriSiswa
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(DatabaseSiswa.DatabaseSiswa.getDatabase(context).SiswaDao())
    }
}