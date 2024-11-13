package com.inforcap.exampleroomapp.view.ui

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.inforcap.exampleroomapp.data.EmployeeEntity
import com.inforcap.exampleroomapp.databinding.ItemEmployeeBinding

class EmployeeAdapter : RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    private lateinit var binding: ItemEmployeeBinding
    lateinit var employeeList: List<EmployeeEntity>
    lateinit var onLongClickListener: ((EmployeeEntity) -> Unit)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeViewHolder(binding)
    }

    override fun getItemCount(): Int = employeeList.size

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.onBind(employeeList[position])
    }


    inner class EmployeeViewHolder(binding: ItemEmployeeBinding) : ViewHolder(binding.root) {
        fun onBind(employeeEntity: EmployeeEntity) {
            val formatoSueldo: DecimalFormat = DecimalFormat("$#,###")
            binding.run {
                tvNombre.text = employeeEntity.nombre + " " + employeeEntity.apellido
                tvEmail.text = "Email: " + employeeEntity.email
                tvSueldo.text = "Sueldo: " + formatoSueldo.format(employeeEntity.sueldo)
                tvDireccion.text = "Dirección: " + employeeEntity.direccion.direccion + ", " + employeeEntity.direccion.ciudad
                tvFechaContrato.text = "Fecha Contrato: " + employeeEntity.createdDateFormatted

                clItem.setOnLongClickListener {
                    onLongClickListener.invoke(employeeEntity)
                    false
                }
            }
        }
    }


}