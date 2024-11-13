package com.inforcap.exampleroomapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [EmployeeEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class EmployeeDataBase: RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

}