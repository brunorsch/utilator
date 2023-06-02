package br.dev.brunnofdc.utilator.exception

class ValidacaoException(
    val mensagemUsuario: Int,
    contexto: String? = null,
) : RuntimeException(contexto)