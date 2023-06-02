package br.dev.brunnofdc.utilator.data.database

import androidx.room.TypeConverter
import java.time.LocalDateTime

class CustomTypeConverters {
    @TypeConverter
    fun fromLocalDateTime(localDate: LocalDateTime): String {
        return localDate.toString()
    }

    @TypeConverter
    fun toLocalDateTime(timestamp: String): LocalDateTime {
        return LocalDateTime.parse(timestamp)
    }
}