package cl.duouc.reservasport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import cl.duouc.reservasport.data.ReservaDatabase
import cl.duouc.reservasport.data.repository.ReservaRepository
import cl.duouc.reservasport.ui.ReservaScreen
import cl.duouc.reservasport.ui.ReservaViewModel
import cl.duouc.reservasport.ui.ReservaViewModelFactory
import cl.duouc.reservasport.ui.theme.ReservaSportTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ReservaViewModel by viewModels {
        val dao = ReservaDatabase
            .obtenerDatabase(applicationContext)
            .reservaDao()

        ReservaViewModelFactory(
            ReservaRepository(dao)
        )
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        setContent {
            ReservaSportTheme {
                ReservaScreen(viewModel)
            }
        }
    }
}
