package com.inforcap.exampleroomapp.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.inforcap.exampleroomapp.data.EmployeeEntity
import com.inforcap.exampleroomapp.databinding.ActivityMainBinding
import com.inforcap.exampleroomapp.view.EmployeeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: EmployeeViewModel

    @Inject
    lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getEmployeeList()

        binding.fabAddEmployee.setOnClickListener {
            startActivity(Intent(this,NewEmployeeActivity::class.java))
        }
    }

    private fun getEmployeeList() {
        viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        viewModel.employeeListLiveData?.observe(this, Observer {
            initRecyclerView(it)
        })
    }

    private fun initRecyclerView(employeeList: List<EmployeeEntity>) {
        adapter = EmployeeAdapter()
        adapter.employeeList = employeeList
        binding.rvEmpleados.layoutManager = LinearLayoutManager(this)
        binding.rvEmpleados.adapter = adapter

        adapter.onLongClickListener = {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setMessage("Estás seguro que deseas eliminar al empleado seleccionado?")
                .setCancelable(false)
                .setPositiveButton("Si") {
                    dialog, id ->
                        deleteEmployee(it)
                }
                .setNegativeButton("Cancelar") { dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    private fun deleteEmployee(employeeEntity: EmployeeEntity) {
        lifecycleScope.launchWhenCreated {
            viewModel.deleteEmployee(employeeEntity)
        }
    }


}