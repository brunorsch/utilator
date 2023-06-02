package br.dev.brunnofdc.utilator.domain

import java.math.BigDecimal

enum class TipoConta(
    val unidadeMedida: String,
    val digitosLeitura: Int,
    val precoUnitario: BigDecimal? = null,
) {
    LUZ("kWh", 5, BigDecimal("0.347078").plus(BigDecimal("0.47"))),
    AGUA("m³", 6),
}