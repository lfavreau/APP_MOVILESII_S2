package cl.duouc.reservasport.domain

import org.junit.Assert.assertThrows
import org.junit.Test

class ReservaValidatorTest {

    @Test
    fun validar_datosCorrectos_noLanzaExcepcion() {
        ReservaValidator.validar(
            nombre = "Luciano",
            cancha = "Tenis 1",
            fecha = "20/08/2026",
            hora = "18:00",
            estado = "Pendiente"
        )
    }

    @Test
    fun validar_nombreVacio_lanzaExcepcion() {
        assertThrows(
            IllegalArgumentException::class.java
        ) {
            ReservaValidator.validar(
                nombre = "",
                cancha = "Tenis 1",
                fecha = "20/08/2026",
                hora = "18:00",
                estado = "Pendiente"
            )
        }
    }

    @Test
    fun validar_canchaVacia_lanzaExcepcion() {
        assertThrows(
            IllegalArgumentException::class.java
        ) {
            ReservaValidator.validar(
                nombre = "Luciano",
                cancha = "",
                fecha = "20/08/2026",
                hora = "18:00",
                estado = "Pendiente"
            )
        }
    }
}
