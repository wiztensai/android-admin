package com.jetwiz.admin

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang")
class ModelBarang {
    @PrimaryKey(autoGenerate = true)
    var roomId = 0

    var id = 0
    var name = ""

    constructor(id:Int, name:String) {
        this.id = id
        this.name = name
    }
}