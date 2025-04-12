package com.gerenciadordecontas.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Conta(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nome: String,
    val valor: Double,
    val status: String // "Pago" ou "Pendente"
)
