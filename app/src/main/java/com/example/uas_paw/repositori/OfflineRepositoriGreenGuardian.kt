package com.example.uas_paw.repositori

import com.example.uas_paw.data.CatatanPemantauan
import com.example.uas_paw.data.CatatanPemantauanDao
import com.example.uas_paw.data.SensorTanaman
import com.example.uas_paw.data.SensorTanamanDao
import com.example.uas_paw.data.Tanaman
import com.example.uas_paw.data.TanamanDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriGreenGuardian(
    private val tanamanDao: TanamanDao,
    private val sensorTanamanDao: SensorTanamanDao,
    private val catatanPemantauanDao: CatatanPemantauanDao
) : RepositoriTanaman, RepositoriSensorTanaman, RepositoriCatatanPemantauan {
    override fun getAllCatatanPemantauanStream(): Flow<List<CatatanPemantauan>> = catatanPemantauanDao.getAllCatatanPemantauan()

    override fun getCatatanPemantauanStream(id: Int): Flow<CatatanPemantauan?> = catatanPemantauanDao.getCatatanPemantauan(id)

    override suspend fun insertCatatanPemantauan(catatanPemantauan: CatatanPemantauan) = catatanPemantauanDao.insert(catatanPemantauan)

    override suspend fun deleteCatatanPemantauan(catatanPemantauan: CatatanPemantauan) = catatanPemantauanDao.delete(catatanPemantauan)

    override suspend fun updateCatatanPemantauan(catatanPemantauan: CatatanPemantauan) = catatanPemantauanDao.update(catatanPemantauan)

    override fun getAllSensorTanamanStream(): Flow<List<SensorTanaman>> = sensorTanamanDao.getAllSensorTanaman()

    override fun getSensorTanamanStream(id: Int): Flow<SensorTanaman?> = sensorTanamanDao.getSensorTanaman(id)

    override suspend fun insertSensorTanaman(sensorTanaman: SensorTanaman) = sensorTanamanDao.insert(sensorTanaman)

    override suspend fun deleteSensorTanaman(sensorTanaman: SensorTanaman) = sensorTanamanDao.delete(sensorTanaman)

    override suspend fun updateSensorTanaman(sensorTanaman: SensorTanaman) = sensorTanamanDao.update(sensorTanaman)

    override fun getAllTanamanStream(): Flow<List<Tanaman>> = tanamanDao.getAllTanaman()

    override fun getTanamanStream(id: Int): Flow<Tanaman?> = tanamanDao.getTanaman(id)

    override suspend fun insertTanaman(tanaman: Tanaman) = tanamanDao.insert(tanaman)

    override suspend fun deleteTanaman(tanaman: Tanaman) = tanamanDao.delete(tanaman)

    override suspend fun updateTanaman(tanaman: Tanaman) = tanamanDao.update(tanaman)

}