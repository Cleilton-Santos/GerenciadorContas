package com.gerenciadordecontas.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.gerenciadordecontas.model.Conta

class ContaViewModel : ViewModel() {

    // Lista de contas
    private val _contas = mutableStateListOf<Conta>()
    val contas: List<Conta> get() = _contas

    // Adicionar uma nova conta
    fun addConta(conta: Conta) {
        _contas.add(conta)
    }

    // Atualizar uma conta existente
    fun updateConta(conta: Conta) {
        val index = _contas.indexOfFirst { it.id == conta.id }
        if (index != -1) {
            _contas[index] = conta
        }
    }
}
