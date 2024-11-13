package com.inforcap.exampleroomapp.di

import android.content.Context
import androidx.room.Room
import com.inforcap.exampleroomapp.data.EmployeeDao
import com.inforcap.exampleroomapp.data.EmployeeDataBase
import com.inforcap.exampleroomapp.view.ui.EmployeeAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBaseModule {

    private const val EMPLOYEES_DATABASE_NAME = "employees_database"

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            EmployeeDataBase::class.java,
            EMPLOYEES_DATABASE_NAME
        ).build()


    @Provides
    fun provideEmployeeDao(db: EmployeeDataBase): EmployeeDao = db.employeeDao()

    @Provides
    fun provideAdapter(): EmployeeAdapter = EmployeeAdapter()
}