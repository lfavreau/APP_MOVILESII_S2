package cl.duouc.reservasport.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Reserva::class],
    version = 2,
    exportSchema = false
)
abstract class ReservaDatabase : RoomDatabase() {

    abstract fun reservaDao(): ReservaDao

    companion object {
        @Volatile
        private var INSTANCE: ReservaDatabase? = null

        fun obtenerDatabase(context: Context): ReservaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    ReservaDatabase::class.java,
                    "reservasport_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instancia
                instancia
            }
        }
    }
}
