package br.dev.brunnofdc.utilator.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.dev.brunnofdc.utilator.R.string.badge_no_mes
import br.dev.brunnofdc.utilator.R.string.btn_nova_leitura
import br.dev.brunnofdc.utilator.R.string.consumo_luz
import br.dev.brunnofdc.utilator.R.string.desc_icone_add
import br.dev.brunnofdc.utilator.R.string.sem_registros
import br.dev.brunnofdc.utilator.domain.TipoConta
import br.dev.brunnofdc.utilator.presentation.ResumoContaViewModel
import br.dev.brunnofdc.utilator.ui.components.bottomsheet.RegistroLeituraBottomSheet
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardResumoConta(tipoConta: TipoConta) {
    val vm = koinViewModel<ResumoContaViewModel> {
        parametersOf(tipoConta)
    }

    val coroutineScope = rememberCoroutineScope()

    val registroBottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp,
            focusedElevation = 8.dp,
        )
    ) {
        Column {
            Container {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(consumo_luz),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(
                            text = stringResource(badge_no_mes),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp),
                            color = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                    Text(
                        text = vm.consumoState.value
                            ?.toString()
                            ?.plus(" ${tipoConta.unidadeMedida}") ?: stringResource(sem_registros),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
            Divider(thickness = 0.8.dp)
            Container {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    onClick = {
                        coroutineScope.launch {
                            registroBottomSheetState.show()
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(desc_icone_add),
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                    Text(
                        color  = MaterialTheme.colorScheme.onSecondary,
                        text = stringResource(btn_nova_leitura),
                    )
                }
            }
        }
    }

    if(registroBottomSheetState.isVisible) {
        RegistroLeituraBottomSheet(registroBottomSheetState, tipoConta.digitosLeitura) {
            it?.let {
                vm.salvarNovaLeitura(it)
            }
        }
    }
}

@Composable
fun Container(content: @Composable () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        content()
    }
}
