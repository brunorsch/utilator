package br.dev.brunnofdc.utilator.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.dev.brunnofdc.utilator.data.LeituraDao
import br.dev.brunnofdc.utilator.domain.Leitura
import br.dev.brunnofdc.utilator.domain.TipoConta
import br.dev.brunnofdc.utilator.domain.service.OperacoesLeiturasService
import kotlinx.coroutines.launch

class ResumoContaViewModel(
    private val tipoConta: TipoConta,
    private val operacoesLeiturasService: OperacoesLeiturasService,
    private val leituraDao: LeituraDao,
) : ViewModel() {
    val consumoState = mutableStateOf<Int?>(null)

    init {
        viewModelScope.launch {
            atualizarStateConsumo()
        }
    }

    fun salvarNovaLeitura(novaLeitura: String) {
        val leitura = Leitura(
            tipoConta = tipoConta,
            valor = novaLeitura.toInt(),
        )

        viewModelScope.launch {
            leituraDao.inserir(leitura)
            atualizarStateConsumo()
        }
    }

    private suspend fun atualizarStateConsumo() {
        consumoState.value = buscarConsumo()
    }

    private suspend fun buscarConsumo() = operacoesLeiturasService
        .buscarLeiturasDoMes(tipoConta)
        .let { leituras -> operacoesLeiturasService.calcularConsumoTotal(leituras) }
}