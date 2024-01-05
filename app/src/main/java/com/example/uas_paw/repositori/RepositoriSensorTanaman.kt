package com.example.uas_paw.repositori

import com.example.uas_paw.data.SensorTanaman
import kotlinx.coroutines.flow.Flow

interface RepositoriSensorTanaman {
    fun getAllSensorTanamanStream(): Flow<List<SensorTanaman>>

    fun getSensorTanamanStream(id: Int): Flow<SensorTanaman?>

    suspend fun insertSensorTanaman(sensorTanaman: SensorTanaman)

    suspend fun deleteSensorTanaman(sensorTanaman: SensorTanaman)

    suspend fun updateSensorTanaman(sensorTanaman: SensorTanaman)
}