package com.example.uas_pam.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repositori.RepositoriCatatanPemantauan
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CatatanPemantauanEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriCatatanPemantauan: RepositoriCatatanPemantauan
) : ViewModel() {
    var CatatanPemantauanUiState by mutableStateOf(UIStateCatatanPemantauan())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            CatatanPemantauanUiState = repositoriCatatanPemantauan.getCatatanPemantauanStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateCatatanPemantauan(true)
        }
    }

    suspend fun updateCatatanPemantauan() {
        if (validasiInput(CatatanPemantauanUiState.detailCatatanPemantauan)) {
            repositoriCatatanPemantauan.updateCatatanPemantauan(CatatanPemantauanUiState.detailCatatanPemantauan.toCatatanPemantauan())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailCatatanPemantauan: DetailCatatanPemantauan) {
        CatatanPemantauanUiState =
            UIStateCatatanPemantauan(detailCatatanPemantauan = detailCatatanPemantauan, isEntryValid = validasiInput(detailCatatanPemantauan))
    }

    private fun validasiInput(uiState: DetailCatatanPemantauan = CatatanPemantauanUiState.detailCatatanPemantauan ): Boolean {
        return with(uiState) {
            tglpemantau.isNotBlank() && kondisitnm.isNotBlank() && keterangan.isNotBlank()
        }
    }
}