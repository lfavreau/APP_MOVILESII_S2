package cl.duouc.reservasport.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ReservaDao {

    @Query("SELECT * FROM reservas ORDER BY id DESC")
    fun obtenerReservas(): Flow<List<Reserva>>

    @Insert
    suspend fun insertarReserva(reserva: Reserva)

    @Update
    suspend fun actualizarReserva(reserva: Reserva)

    @Delete
    suspend fun eliminarReserva(reserva: Reserva)
}
