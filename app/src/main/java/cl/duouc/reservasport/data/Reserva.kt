package cl.duouc.reservasport.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reservas")
data class Reserva(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombreUsuario: String,
    val cancha: String,
    val fecha: String,
    val hora: String,
    val estado: String,
    val imagenUrl: String
)
