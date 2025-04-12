package com.gerenciadordecontas.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gerenciadordecontas.model.Conta

@Dao
interface ContaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserir(conta: Conta)

    @Update
    suspend fun atualizar(conta: Conta)

    @Delete
    suspend fun deletar(conta: Conta)

    @Query("SELECT * FROM Conta ORDER BY id DESC")
    fun listarContas(): LiveData<List<Conta>>
}
