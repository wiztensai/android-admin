package com.jetwiz.admin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetwiz.admin.adapter.BarangAdapter
import com.jetwiz.admin.contracts.TambahEditBarangListener
import com.jetwiz.admin.database.DB_General
import com.jetwiz.admin.databinding.AMainBinding
import com.jetwiz.admin.dialog.DF_TambahEditBarang
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import wazma.punjabi.helper.H_Coroutine

class A_Main: AppCompatActivity() {

    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + H_Coroutine.getErrorHandler()
    )

    lateinit var bind:AMainBinding
    val roomDB by lazy { DB_General.getAppDatabase(this) }

    companion object {
        lateinit var instance:A_Main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instance = this

        bind = AMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.recyclerView.layoutManager = LinearLayoutManager(this@A_Main)
        coroutineScope.launch {
            initRvData()
        }

        bind.btnTambahBarang.setOnClickListener {
            DF_TambahEditBarang(object : TambahEditBarangListener {
                override fun onAdd() {
                    coroutineScope.launch {
                        initRvData()
                    }
                }

                override fun onEdit(modelBarang: ModelBarang) {
                    // nothing
                }
            }, null).show(supportFragmentManager, null)
        }
    }

    suspend fun initRvData() {
        val barangs = roomDB.barangDao().getBarangs()
        val sorted = barangs.sortedBy {
            it.name
        }
        bind.recyclerView.adapter = BarangAdapter(sorted.toMutableList())
    }
}