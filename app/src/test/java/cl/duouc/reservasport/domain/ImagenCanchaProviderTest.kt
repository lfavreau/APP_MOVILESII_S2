package cl.duouc.reservasport.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class ImagenCanchaProviderTest {

    @Test
    fun obtener_canchaTenis_retornaImagenTenis() {
        val resultado = ImagenCanchaProvider.obtener(
            "Tenis 2"
        )

        assertEquals(
            "https://picsum.photos/id/26/800/400",
            resultado
        )
    }

    @Test
    fun obtener_canchaFutbol_retornaImagenFutbol() {
        val resultado = ImagenCanchaProvider.obtener(
            "Fútbol 7"
        )

        assertEquals(
            "https://picsum.photos/id/28/800/400",
            resultado
        )
    }

    @Test
    fun obtener_canchaDesconocida_retornaImagenPredeterminada() {
        val resultado = ImagenCanchaProvider.obtener(
            "Multicancha"
        )

        assertEquals(
            "https://picsum.photos/id/31/800/400",
            resultado
        )
    }
}
