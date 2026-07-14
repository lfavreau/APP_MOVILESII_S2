package cl.duouc.reservasport

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class ReservaScreenTest {

    @get:Rule
    val composeRule =
        createAndroidComposeRule<MainActivity>()

    @Test
    fun nuevaReserva_abreFormulario() {
        composeRule
            .onNodeWithText("Nueva reserva")
            .performClick()

        composeRule
            .onNodeWithText("Nombre del usuario")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Cancha")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Fecha")
            .assertIsDisplayed()

        composeRule
            .onNodeWithText("Hora")
            .assertIsDisplayed()
    }

    @Test
    fun formularioVacio_muestraMensajeValidacion() {
        composeRule
            .onNodeWithText("Nueva reserva")
            .performClick()

        composeRule
            .onNodeWithText("Guardar")
            .performClick()

        composeRule
            .onNodeWithText(
                "Completa todos los campos antes de guardar."
            )
            .assertIsDisplayed()
    }
}
