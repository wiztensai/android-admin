package com.jetwiz.admin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
class ModelBarang {
    @PrimaryKey(autoGenerate = true)
    var id = 0

    var name = ""

    constructor(name: String) {
        this.name = name
    }
}