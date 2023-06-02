package br.dev.brunnofdc.utilator.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.dev.brunnofdc.utilator.domain.Leitura
import br.dev.brunnofdc.utilator.domain.TipoConta
import java.time.LocalDateTime

@Dao
interface LeituraDao {
    @Insert
    suspend fun inserir(leitura: Leitura)

    @Query("SELECT * FROM leitura WHERE tipoConta = :tipoConta AND data BETWEEN :inicio AND :fim")
    suspend fun buscarTodosPorIntervalo(tipoConta: TipoConta, inicio: LocalDateTime, fim: LocalDateTime): List<Leitura>
}