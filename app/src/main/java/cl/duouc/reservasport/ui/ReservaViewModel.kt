package cl.duouc.reservasport.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duouc.reservasport.data.Reserva
import cl.duouc.reservasport.data.repository.ReservaRepository
import cl.duouc.reservasport.domain.ImagenCanchaProvider
import cl.duouc.reservasport.domain.ReservaValidator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ReservaViewModel(
    private val repository: ReservaRepository
) : ViewModel() {

    companion object {
        private const val TAG = "ReservaSportDebug"
    }

    val reservas = repository.obtenerReservas()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    var isLoading by mutableStateOf(false)
        private set

    var mensajeOperacion by mutableStateOf("")
        private set

    var mensajeError by mutableStateOf("")
        private set

    fun guardarReserva(
        nombre: String,
        cancha: String,
        fecha: String,
        hora: String,
        estado: String
    ) {
        viewModelScope.launch {
            iniciarProceso("Guardando reserva...")

            try {
                Log.d(TAG, "Entrando a guardarReserva")

                ReservaValidator.validar(
                    nombre = nombre,
                    cancha = cancha,
                    fecha = fecha,
                    hora = hora,
                    estado = estado
                )

                val reserva = Reserva(
                    nombreUsuario = nombre.trim(),
                    cancha = cancha.trim(),
                    fecha = fecha.trim(),
                    hora = hora.trim(),
                    estado = estado.trim(),
                    imagenUrl = ImagenCanchaProvider.obtener(cancha)
                )

                withContext(Dispatchers.IO) {
                    Log.d(TAG, "Insertando reserva en Dispatchers.IO")
                    delay(1000)
                    repository.insertar(reserva)
                }

                mensajeOperacion = "Reserva guardada correctamente"
                Log.i(TAG, "Reserva guardada correctamente")
            } catch (e: IllegalArgumentException) {
                manejarError("Datos inválidos", e)
            } catch (e: Exception) {
                manejarError("Error inesperado al guardar reserva", e)
            } finally {
                finalizarProceso("Finaliza guardarReserva")
            }
        }
    }

    fun actualizarReserva(
        reserva: Reserva,
        nombre: String,
        cancha: String,
        fecha: String,
        hora: String,
        estado: String
    ) {
        viewModelScope.launch {
            iniciarProceso("Actualizando reserva...")

            try {
                Log.d(TAG, "Entrando a actualizarReserva ID: ${reserva.id}")

                ReservaValidator.validar(
                    nombre = nombre,
                    cancha = cancha,
                    fecha = fecha,
                    hora = hora,
                    estado = estado
                )

                val reservaActualizada = reserva.copy(
                    nombreUsuario = nombre.trim(),
                    cancha = cancha.trim(),
                    fecha = fecha.trim(),
                    hora = hora.trim(),
                    estado = estado.trim(),
                    imagenUrl = ImagenCanchaProvider.obtener(cancha)
                )

                withContext(Dispatchers.IO) {
                    Log.d(TAG, "Actualizando reserva en Dispatchers.IO")
                    delay(1000)
                    repository.actualizar(reservaActualizada)
                }

                mensajeOperacion = "Reserva actualizada correctamente"
                Log.i(TAG, "Reserva actualizada correctamente")
            } catch (e: IllegalArgumentException) {
                manejarError("Datos inválidos", e)
            } catch (e: Exception) {
                manejarError("Error inesperado al actualizar reserva", e)
            } finally {
                finalizarProceso("Finaliza actualizarReserva")
            }
        }
    }

    fun eliminarReserva(reserva: Reserva) {
        viewModelScope.launch {
            iniciarProceso("Eliminando reserva...")

            try {
                Log.d(TAG, "Entrando a eliminarReserva ID: ${reserva.id}")

                withContext(Dispatchers.IO) {
                    Log.d(TAG, "Eliminando reserva en Dispatchers.IO")
                    delay(1000)
                    repository.eliminar(reserva)
                }

                mensajeOperacion = "Reserva eliminada correctamente"
                Log.i(TAG, "Reserva eliminada correctamente")
            } catch (e: Exception) {
                manejarError("Error inesperado al eliminar reserva", e)
            } finally {
                finalizarProceso("Finaliza eliminarReserva")
            }
        }
    }

    private fun iniciarProceso(mensaje: String) {
        isLoading = true
        mensajeOperacion = mensaje
        mensajeError = ""
        Log.d(TAG, mensaje)
    }

    private fun finalizarProceso(mensaje: String) {
        isLoading = false
        Log.d(TAG, mensaje)
    }

    private fun manejarError(
        mensaje: String,
        exception: Exception
    ) {
        mensajeOperacion = ""
        mensajeError = "$mensaje: ${exception.message}"
        Log.e(TAG, mensaje, exception)
    }
}
