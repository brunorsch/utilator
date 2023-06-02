package br.dev.brunnofdc.utilator.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.dev.brunnofdc.utilator.ui.components.TopBar

@Composable
fun BaseScreen(content: @Composable (snackbar: SnackbarHostState) -> Unit) {
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            topBar = { TopBar() },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Box(modifier = Modifier.padding(8.dp)) {
                        content(snackbarHostState)
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetBaseScreen(
    bottomSheet: @Composable () -> Unit,
    content: @Composable (snackbar: SnackbarHostState) -> Unit) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { TopBar() },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Box(modifier = Modifier.padding(8.dp)) {
                    content(snackbarHostState)
                }
            }
        },
        sheetContent = {
            bottomSheet()
        }
    )
}