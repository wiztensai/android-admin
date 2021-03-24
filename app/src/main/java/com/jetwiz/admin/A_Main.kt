package com.jetwiz.admin

import android.app.Activity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetwiz.admin.adapter.BarangAdapter
import com.jetwiz.admin.database.DB_General
import com.jetwiz.admin.databinding.AMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import wazma.punjabi.helper.H_Coroutine

class A_Main: Activity() {

    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + H_Coroutine.getErrorHandler()
    )

    lateinit var bind:AMainBinding
    val roomDB by lazy { DB_General.getAppDatabase(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bind = AMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        coroutineScope.launch {
            val barangs = roomDB.barangDao().getBarangs()
            bind.recyclerView.adapter = BarangAdapter(barangs)
            bind.recyclerView.layoutManager = LinearLayoutManager(this@A_Main)
        }
    }
}