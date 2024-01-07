package com.example.uas_pam.repositori

import com.example.uas_pam.data.Tanaman
import kotlinx.coroutines.flow.Flow

interface RepositoriTanaman {
    fun getAllTanamanStream(): Flow<List<Tanaman>>

    fun getTanamanStream(id: Int): Flow<Tanaman?>

    suspend fun insertTanaman(tanaman: Tanaman)

    suspend fun deleteTanaman(tanaman: Tanaman)

    suspend fun updateTanaman(tanaman: Tanaman)
}