package com.example.uas_paw.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_paw.data.Tanaman
import com.example.uas_paw.repositori.RepositoriTanaman
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TanamanHomeViewModel(private val repositoriTanaman: RepositoriTanaman) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val TanamanHomeUiState: StateFlow<TanamanHomeUiState> = repositoriTanaman.getAllTanamanStream().filterNotNull()
        .map { TanamanHomeUiState(listTanaman = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = TanamanHomeUiState()
        )


}

data class TanamanHomeUiState(
    val listTanaman: List<Tanaman> = listOf()
)