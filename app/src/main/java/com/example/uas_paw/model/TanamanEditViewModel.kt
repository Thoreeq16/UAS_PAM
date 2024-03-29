package com.example.uas_paw.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_paw.repositori.RepositoriTanaman
import com.example.uas_paw.ui.halaman.TanamanEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class TanamanEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriTanaman: RepositoriTanaman
) : ViewModel() {
    var TanamanUiState by mutableStateOf(UIStateTanaman())
        private set

    private val TanamanId: Int = checkNotNull(savedStateHandle[TanamanEditDestination.TanamanIdArg])

    init {
        viewModelScope.launch {
            TanamanUiState = repositoriTanaman.getTanamanStream(TanamanId)
                .filterNotNull()
                .first()
                .toUiStateTanaman(true)
        }
    }

    suspend fun updateTanaman() {
        if (validasiInput(TanamanUiState.detailTanaman)) {
            repositoriTanaman.updateTanaman(TanamanUiState.detailTanaman.toTanaman())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailTanaman: DetailTanaman) {
        TanamanUiState =
            UIStateTanaman(detailTanaman = detailTanaman, isEntryValid = validasiInput(detailTanaman))
    }

    private fun validasiInput(uiState: DetailTanaman = TanamanUiState.detailTanaman ): Boolean {
        return with(uiState) {
            nmtanaman.isNotBlank() && jnstanaman.isNotBlank() && loctanaman.isNotBlank() && wkttanam.isNotBlank()
        }
    }
}