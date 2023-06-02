package br.dev.brunnofdc.utilator.domain.service

import br.dev.brunnofdc.utilator.data.LeituraDao
import br.dev.brunnofdc.utilator.domain.Leitura
import br.dev.brunnofdc.utilator.domain.TipoConta
import br.dev.brunnofdc.utilator.utils.atEndOfDay
import java.math.BigDecimal
import java.time.LocalDate

class OperacoesLeiturasService(
    val leituraDao: LeituraDao,
) {
    suspend fun buscarLeiturasDoMes(tipoConta: TipoConta): List<Leitura> {
        return leituraDao.buscarTodosPorIntervalo(
            tipoConta,
            LocalDate.now().withDayOfMonth(1).atStartOfDay(),
            LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atEndOfDay()
        )
    }

    fun calcularConsumoTotal(leituras: List<Leitura>): Int? {
        if(leituras.isEmpty()) return null

        val consumoTotalCalculado = aplicarFuncaoAcumuladora(leituras) { calcularConsumoDesde(it) }

        return consumoTotalCalculado.sumOf { it }
    }

    fun calcularCustoTotal(leituras: List<Leitura>): BigDecimal? {
        return aplicarFuncaoAcumuladora(leituras) { calcularCustoConsumoDesde(it) }
            .map { it ?: return null }
            .sumOf { it }
    }

    private fun <T> aplicarFuncaoAcumuladora(leituras: List<Leitura>, funcao: Leitura.(Leitura) -> T): List<T> {
        return leituras.mapIndexed { index, it ->
            val leituraAnterior = leituras.getOrNull(index - 1)

            if (leituraAnterior != null) {
                funcao(it, leituraAnterior)
            } else {
                funcao(it, it)
            }
        }
    }
}