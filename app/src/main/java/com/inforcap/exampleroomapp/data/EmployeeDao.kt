package com.inforcap.exampleroomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee_table")
    fun getEmployees(): LiveData<List<EmployeeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    @Query("DELETE FROM employee_table WHERE id= :employeeId")
    suspend fun deleteEmployee(employeeId: Int)

    @Query("UPDATE employee_table SET nombre= :nombre, apellido = :apellido WHERE id = :employeeId")
    suspend fun updateEmployee(nombre: String, apellido: String, employeeId: Int)


}