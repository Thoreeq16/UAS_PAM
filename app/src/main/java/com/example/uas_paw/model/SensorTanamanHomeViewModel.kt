package com.example.uas_paw.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_paw.data.SensorTanaman
import com.example.uas_paw.repositori.RepositoriSensorTanaman
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SensorTanamanHomeViewModel(private val repositoriSensorTanaman: RepositoriSensorTanaman) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val SensorTanamanHomeUiState: StateFlow<SensorTanamanHomeUiState> = repositoriSensorTanaman.getAllSensorTanamanStream().filterNotNull()
        .map { SensorTanamanHomeUiState(listSensorTanaman = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = SensorTanamanHomeUiState()
        )


}

data class SensorTanamanHomeUiState(
    val listSensorTanaman: List<SensorTanaman> = listOf()
)