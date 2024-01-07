package com.example.uas_pam.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repositori.RepositoriSensorTanaman
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class SensorTanamanDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSensorTanaman: RepositoriSensorTanaman
) : ViewModel() {

    private val SensorTanamanID: Int = checkNotNull(savedStateHandle[DetailDestination.SensorTanamanIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        repositoriSensorTanaman.getSensorTanamanStream(SensorTanamanID).filterNotNull().map {
            ItemDetailUiState(detailSensorTanaman = it.toDetailSensorTanaman())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailUiState()
        )

    suspend fun deleteItem() {
        repositoriSensorTanaman.deleteSensorTanaman(uiState.value.detailSensorTanaman.toSensorTanaman())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class  ItemDetailUiState(
    val outOfStock: Boolean = true,
    val detailSensorTanaman: DetailSensorTanaman = DetailSensorTanaman()
)