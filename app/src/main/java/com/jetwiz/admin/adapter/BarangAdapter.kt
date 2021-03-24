package com.jetwiz.admin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jetwiz.admin.A_Main
import com.jetwiz.admin.ModelBarang
import com.jetwiz.admin.R
import com.jetwiz.admin.contracts.TambahEditBarangListener
import com.jetwiz.admin.database.DB_General
import com.jetwiz.admin.dialog.DF_TambahEditBarang
import kotlinx.coroutines.*
import wazma.punjabi.helper.H_Coroutine

class BarangAdapter(private val dataSet: MutableList<ModelBarang>):RecyclerView.Adapter<BarangAdapter.ViewHolder>() {

    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + H_Coroutine.getErrorHandler()
    )

    lateinit var roomDB: DB_General

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        roomDB = DB_General.getAppDatabase(recyclerView.context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName:TextView
        val btnDel:Button
        val btnEdit:Button

        init {
            tvName = view.findViewById(R.id.tvName)
            btnDel = view.findViewById(R.id.btnDel)
            btnEdit = view.findViewById(R.id.btnEdit)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_barang, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = dataSet[position].name
        holder.btnDel.setOnClickListener {
            coroutineScope.launch {
                roomDB.barangDao().deleteById(dataSet[holder.layoutPosition].id)
//                notifyItemRemoved(position)
                notifyDataSetChanged()
                dataSet.removeAt(holder.layoutPosition)
            }
        }
        holder.btnEdit.setOnClickListener {
            DF_TambahEditBarang(object : TambahEditBarangListener {
                override fun onAdd() {
                    // nothing
                }

                override fun onEdit(modelBarang: ModelBarang) {
                    dataSet[position] = modelBarang
                    notifyDataSetChanged()
                }
            }, dataSet[position]).show(A_Main.instance.supportFragmentManager, null)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}