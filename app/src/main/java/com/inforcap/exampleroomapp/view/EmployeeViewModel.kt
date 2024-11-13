package com.inforcap.exampleroomapp.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inforcap.exampleroomapp.data.EmployeeEntity
import com.inforcap.exampleroomapp.repository.EmployeeRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepositoryImp
) : ViewModel() {

    var employeeListLiveData: LiveData<List<EmployeeEntity>>? = MutableLiveData(emptyList())


    init {
        viewModelScope.launch {
            employeeListLiveData = repository.getEmployees()
        }
    }

    suspend fun addEmployee(employeeEntity: EmployeeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addEmployee(employeeEntity)
        }
    }

    suspend fun deleteEmployee(employeeEntity: EmployeeEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEmployee(employeeEntity)
        }
    }


}
