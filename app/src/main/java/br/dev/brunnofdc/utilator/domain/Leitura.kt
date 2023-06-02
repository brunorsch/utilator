package br.dev.brunnofdc.utilator.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import br.dev.brunnofdc.utilator.R
import br.dev.brunnofdc.utilator.exception.ValidacaoException
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
data class Leitura(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val tipoConta: TipoConta,
    @ColumnInfo val valor: Int,
    @ColumnInfo val data: LocalDateTime = LocalDateTime.now(),
) {
    init {
        require(valor >= 0) { ValidacaoException(R.string.erro_leitura_menor_que_zero) }
    }

    fun calcularConsumoDesde(leituraAnterior: Leitura): Int {
        return this.valor - leituraAnterior.valor
    }

    fun calcularCustoConsumoDesde(leituraAnterior: Leitura): BigDecimal? {
        val consumo = calcularConsumoDesde(leituraAnterior)

        if(tipoConta.precoUnitario == null) {
            return null
        }

        return tipoConta.precoUnitario.multiply(BigDecimal(consumo))
    }
}