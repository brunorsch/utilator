package br.dev.brunnofdc.utilator.ui.screen.home;

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.dev.brunnofdc.utilator.domain.TipoConta.LUZ
import br.dev.brunnofdc.utilator.ui.screen.BaseScreen

@Composable
fun HomeScreen() {

    BaseScreen {
        Column {
            CardResumoConta(LUZ)
        }
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    HomeScreen()
}