package br.dev.brunnofdc.utilator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.dev.brunnofdc.utilator.data.LeituraDao
import br.dev.brunnofdc.utilator.domain.Leitura

@Database(version = 1, entities = [
    Leitura::class,
])
@TypeConverters(CustomTypeConverters::class)
abstract class UtilatorDatabase : RoomDatabase() {
   abstract fun leituraDao(): LeituraDao
}