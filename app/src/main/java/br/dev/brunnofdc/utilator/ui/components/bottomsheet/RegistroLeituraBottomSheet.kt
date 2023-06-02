package br.dev.brunnofdc.utilator.ui.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.dev.brunnofdc.utilator.R.string.btn_registrar
import br.dev.brunnofdc.utilator.R.string.cta_informe_valor
import br.dev.brunnofdc.utilator.ui.components.input.InputDigitos
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroLeituraBottomSheet(state: SheetState, tamanhoDigitosLeitura: Int, onFinalizado: (String?) -> Unit) {
    val coroutineScope = rememberCoroutineScope()

    val valorLeitura = remember { mutableStateOf("") }

    ModalBottomSheet(
        modifier = Modifier.imePadding(),
        sheetState = state,
        onDismissRequest = {
            coroutineScope.launch {
                state.hide()
            }
            onFinalizado(null)
        },
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Text(text = stringResource(cta_informe_valor), fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                InputDigitos(
                    state = valorLeitura,
                    digitos = tamanhoDigitosLeitura,
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    coroutineScope.launch {
                        state.hide()
                    }

                    onFinalizado(valorLeitura.value)
                },
                enabled = valorLeitura.value.length == tamanhoDigitosLeitura
            ) {
                Text(text = stringResource(btn_registrar))
            }
        }
    }
}