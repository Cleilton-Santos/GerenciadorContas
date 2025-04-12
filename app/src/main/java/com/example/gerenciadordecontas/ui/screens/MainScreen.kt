package com.gerenciadordecontas.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gerenciadordecontas.viewmodel.ContaViewModel
import com.gerenciadordecontas.model.Conta

@Composable
fun MainScreen(viewModel: ContaViewModel) {
    var nomeConta by remember { mutableStateOf("") }
    var valorConta by remember { mutableStateOf("") }
    var contaEditada: Conta? by remember { mutableStateOf(null) }

    val contas = viewModel.contas.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gerenciador de Contas") },
                actions = {
                    // Ação para exibir mais opções
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Campos de entrada para adicionar/atualizar uma conta
                BasicTextField(
                    value = nomeConta,
                    onValueChange = { nomeConta = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                        }
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
                BasicTextField(
                    value = valorConta,
                    onValueChange = { valorConta = it },
                    modifier = Modifier.fillMaxWidth(),
                    decorationBox = { innerTextField ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botão de salvar/atualizar
                Button(
                    onClick = {
                        if (contaEditada != null) {
                            // Atualiza a conta existente
                            viewModel.updateConta(contaEditada!!.copy(nome = nomeConta, valor = valorConta))
                            contaEditada = null
                        } else {
                            // Cria uma nova conta
                            val novaConta = Conta(nome = nomeConta, valor = valorConta)
                            viewModel.addConta(novaConta)
                        }
                        nomeConta = ""
                        valorConta = ""
                    }
                ) {
                    Text(if (contaEditada != null) "Atualizar" else "Adicionar")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Lista de contas
                LazyColumn {
                    items(contas.value) { conta ->
                        ListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            text = { Text(text = conta.nome) },
                            secondaryText = { Text(text = "Valor: ${conta.valor}") },
                            trailing = {
                                IconButton(onClick = {
                                    // Definir a conta para edição
                                    contaEditada = conta
                                    nomeConta = conta.nome
                                    valorConta = conta.valor
                                }) {
                                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                                }
                            }
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    // Chame o viewModel e passe um estado inicial para testar
    MainScreen(viewModel = ContaViewModel())
}
