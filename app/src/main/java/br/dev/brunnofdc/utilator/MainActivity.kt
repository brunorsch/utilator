package br.dev.brunnofdc.utilator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.dev.brunnofdc.utilator.ui.screen.home.HomeScreen
import br.dev.brunnofdc.utilator.ui.theme.UtilatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UtilatorTheme {
                HomeScreen()
            }
        }
    }
}

