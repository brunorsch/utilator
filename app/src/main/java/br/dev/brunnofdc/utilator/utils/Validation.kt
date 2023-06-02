package br.dev.brunnofdc.utilator.utils

import br.dev.brunnofdc.utilator.exception.ValidacaoException

fun require(
    condicao: Boolean,
    lazyException: () -> ValidacaoException,
) {
    if (!condicao) {
        throw lazyException()
    }
}