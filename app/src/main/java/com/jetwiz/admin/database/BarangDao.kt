package com.jetwiz.admin.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.jetwiz.admin.ModelBarang

@Dao
interface BarangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(d: ModelBarang):Long

    @Query("DELETE FROM barang WHERE id=:id")
    suspend fun deleteById(id:Int):Int

    @Update
    suspend fun updateBarang(d: ModelBarang)

    @Delete
    suspend fun deleteMultiBarang(list:MutableList<ModelBarang>)

    @Query("SELECT * FROM barang")
    suspend fun getBarangs(): MutableList<ModelBarang>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserts(d: MutableList<ModelBarang>):LongArray
}