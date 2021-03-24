package com.jetwiz.admin.contracts

import com.jetwiz.admin.ModelBarang

interface TambahEditBarangListener {
    fun onAdd()
    fun onEdit(modelBarang: ModelBarang)
}