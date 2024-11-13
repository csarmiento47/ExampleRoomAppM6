package com.inforcap.exampleroomapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "employee_table")
@Parcelize
data class EmployeeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,

    @ColumnInfo(name = "nombre") val nombre: String,

    @ColumnInfo(name = "apellido") val apellido: String,

    @ColumnInfo(name = "email") val email: String,

    @ColumnInfo(name = "sueldo") val sueldo: Int?,

    @Embedded
    val direccion: @RawValue Direccion,

    @ColumnInfo(name = "fecha_contrato") val fechaContrato: LocalDateTime
) : Parcelable {
    val createdDateFormatted :  String
        get() = fechaContrato.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
}

data class Direccion (
    val direccion: String,
    val ciudad: String,
)
