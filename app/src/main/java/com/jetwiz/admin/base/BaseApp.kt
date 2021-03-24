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
        barangs.add(ModelBarang("Coke"))
        barangs.add(ModelBarang("Pepsi"))
        barangs.add(ModelBarang("Cofee"))
        barangs.add(ModelBarang("Soda"))
        barangs.add(ModelBarang("Cendol"))
        barangs.add(ModelBarang("mie"))
        barangs.add(ModelBarang("kapal api"))
        barangs.add(ModelBarang("nutrisari"))
        barangs.add(ModelBarang("kopi susu"))
        barangs.add(ModelBarang("susu kambing"))
        barangs.add(ModelBarang("susu sapi"))
        barangs.add(ModelBarang("es kepal milo"))
        barangs.add(ModelBarang("cornello"))
        barangs.add(ModelBarang("es campur"))

        return barangs
    }
}