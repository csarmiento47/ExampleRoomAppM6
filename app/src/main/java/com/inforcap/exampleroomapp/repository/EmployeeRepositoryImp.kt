package com.inforcap.exampleroomapp.repository

import androidx.lifecycle.LiveData
import com.inforcap.exampleroomapp.data.EmployeeDao
import com.inforcap.exampleroomapp.data.EmployeeEntity
import javax.inject.Inject

class EmployeeRepositoryImp @Inject constructor(
    private val employeeDao: EmployeeDao
) : EmployeeRepository {

    override suspend fun getEmployees(): LiveData<List<EmployeeEntity>> = employeeDao.getEmployees()

    override suspend fun addEmployee(employeeEntity: EmployeeEntity) = employeeDao.addEmployee(employeeEntity)

    override suspend fun deleteEmployee(employeeEntity: EmployeeEntity) = employeeDao.deleteEmployee(employeeEntity.id)

    override suspend fun updateEmployee(
        employeeEntity: EmployeeEntity,
        updateEmployeeEntity: EmployeeEntity
    ) = employeeDao.updateEmployee(employeeEntity.nombre, employeeEntity.apellido, employeeEntity.id)
}