package com.inforcap.exampleroomapp.view.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.inforcap.exampleroomapp.R
import com.inforcap.exampleroomapp.data.Direccion
import com.inforcap.exampleroomapp.data.EmployeeEntity
import com.inforcap.exampleroomapp.databinding.ActivityNewEmployeeBinding
import com.inforcap.exampleroomapp.view.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@AndroidEntryPoint
class NewEmployeeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewEmployeeBinding
    private val viewModel: EmployeeViewModel by viewModels()
    private var day  : Int = 0
    private var month: Int = 0
    private var year : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNewEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etFechaContrato.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnAddEmployee.setOnClickListener {
            addEmployee()
            finish()
        }


    }

    private fun showDatePickerDialog() {
        val datePickerFragment = DatePickerFragment {
            day, month, year -> onDateSelected(day,month+1, year) }
        datePickerFragment.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year:Int) {
        binding.etFechaContrato.setText("$day/$month/$year")
        this.day = day
        this.month = month
        this.year = year
    }

    private fun addEmployee() {
        lifecycleScope.launch {
            val nombre = binding.etNombre.text.toString()
            val apellido = binding.etApellido.text.toString()
            val email = binding.etEmail.text.toString()
            val sueldo = binding.etSalary.text.toString()
            val direccion = binding.etDireccionNombre.text.toString()
            val ciudad = binding.etCiudad.text.toString()
            viewModel.addEmployee(
                EmployeeEntity(
                    id=0,
                    nombre = nombre,
                    apellido = apellido,
                    email = email,
                    sueldo = sueldo.toInt(),
                    direccion = Direccion(direccion, ciudad),
                    fechaContrato = LocalDateTime.of(year,month,day,0,0)
                )
            )
        }
        Toast.makeText(this,"Empleado registrado satisfactoriamente",Toast.LENGTH_SHORT).show()
    }
}