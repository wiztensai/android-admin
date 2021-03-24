package com.jetwiz.admin.base

import android.app.Application
import com.jetwiz.admin.ModelBarang
import com.jetwiz.admin.database.DB_General
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import wazma.punjabi.helper.H_Coroutine
import wazma.punjabi.helper.H_Prefs

class BaseApp: Application() {

    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + H_Coroutine.getErrorHandler()
    )

    val roomDB by lazy {
        DB_General.getAppDatabase(this)
    }

    override fun onCreate() {
        super.onCreate()

        val prefs = H_Prefs(this)
        val isFirstOpen = prefs.getPrefs().getBoolean(CST.PREF_FIRST_OPEN_B, false)
        if (isFirstOpen == false) {
            coroutineScope.launch {
                roomDB.barangDao().inserts(getBarangs())
                prefs.setData(CST.PREF_FIRST_OPEN_B,true)
            }
        }
    }

    fun getBarangs():MutableList<ModelBarang> {
        val barangs = mutableListOf<ModelBarang>()
        barangs.add(ModelBarang(0, "Coke"))
        barangs.add(ModelBarang(1, "Pepsi"))
        barangs.add(ModelBarang(2, "Cofee"))
        barangs.add(ModelBarang(3, "Soda"))
        barangs.add(ModelBarang(4, "Cendol"))
        barangs.add(ModelBarang(5, "mie"))
        barangs.add(ModelBarang(6, "kapal api"))
        barangs.add(ModelBarang(7, "nutrisari"))
        barangs.add(ModelBarang(8, "kopi susu"))
        barangs.add(ModelBarang(9, "susu kambing"))
        barangs.add(ModelBarang(10, "susu sapi"))
        barangs.add(ModelBarang(12, "es kepal milo"))
        barangs.add(ModelBarang(13, "cornello"))
        barangs.add(ModelBarang(14, "es campur"))

        return barangs
    }
}