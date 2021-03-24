package com.jetwiz.admin.dialog

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.Constraints
import androidx.fragment.app.DialogFragment
import com.jetwiz.admin.ModelBarang
import com.jetwiz.admin.contracts.TambahEditBarangListener
import com.jetwiz.admin.database.DB_General
import com.jetwiz.admin.databinding.DfBarangBinding
import com.jetwiz.admin.utils.U_DpPxConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import wazma.punjabi.helper.H_Coroutine

@SuppressLint("ValidFragment")
class DF_TambahEditBarang(val tambahbaranglistenerEdit: TambahEditBarangListener, var modelIfEdit:ModelBarang?) : DialogFragment() {

    val coroutineScope = CoroutineScope(
        Dispatchers.Main + SupervisorJob() + H_Coroutine.getErrorHandler()
    )

    lateinit var roomDB: DB_General
    private lateinit var bind: DfBarangBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DfBarangBinding.inflate(layoutInflater)
        roomDB = DB_General.getAppDatabase(bind.root.context)
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            it.getWindow()!!.setLayout(Constraints.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.WRAP_CONTENT) // full width dialog
            val back = ColorDrawable(Color.TRANSPARENT)
            val inset = InsetDrawable(back, U_DpPxConverter.dpToPixel(16, it.context))
            it.window!!.setBackgroundDrawable(inset)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (dialog!!.window != null) {
            dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        modelIfEdit?.let {
            bind.etNama.setText(it.name)
        }

        bind.btnCancel.setOnClickListener {
            dismiss()
        }

        bind.btnSubmit.setOnClickListener {
            modelIfEdit?.let {
                editBarang(it)
            }?:also {
                addBarang()
            }
        }
    }

    fun addBarang() {
        if (bind.etNama.text.toString().isNotBlank()) {
            bind.tilNama.error = null

            coroutineScope.launch {
                val nama = bind.etNama.text.toString()
                roomDB.barangDao().insert(ModelBarang(nama))
                tambahbaranglistenerEdit.onAdd()

                dismiss()
            }
        } else {
            bind.tilNama.error = "Nama harus diisi"
        }
    }

    fun editBarang(modelIfEdit: ModelBarang) {
        if (bind.etNama.text.toString().isNotBlank()) {
            bind.tilNama.error = null

            coroutineScope.launch {
                val nama = bind.etNama.text.toString()
                modelIfEdit.name = nama
                roomDB.barangDao().updateBarang(modelIfEdit)
                tambahbaranglistenerEdit.onEdit(modelIfEdit)

                dismiss()
            }
        } else {
            bind.tilNama.error = "Nama harus diisi"
        }
    }
}