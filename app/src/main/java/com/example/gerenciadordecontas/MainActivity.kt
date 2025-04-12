package com.gerenciadordecontas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.gerenciadordecontas.ui.screens.MainScreen
import com.gerenciadordecontas.ui.theme.GerenciadorDeContasTheme
import com.gerenciadordecontas.viewmodel.ContaViewModel

class MainActivity : ComponentActivity() {

    private val contaViewModel: ContaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadorDeContasTheme {
                MainScreen(viewModel = contaViewModel)
            }
        }
    }
}
