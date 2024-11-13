package com.inforcap.exampleroomapp.repository

import androidx.lifecycle.LiveData
import com.inforcap.exampleroomapp.data.EmployeeEntity

interface EmployeeRepository {

    suspend fun getEmployees(): LiveData<List<EmployeeEntity>>

    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    suspend fun deleteEmployee(employeeEntity: EmployeeEntity)

    suspend fun updateEmployee(employeeEntity: EmployeeEntity, updateEmployeeEntity: EmployeeEntity)

}