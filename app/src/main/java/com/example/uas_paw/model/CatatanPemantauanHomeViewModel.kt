package com.example.uas_paw.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_paw.data.CatatanPemantauan
import com.example.uas_paw.repositori.RepositoriCatatanPemantauan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CatatanPemantauanHomeViewModel(private val repositoriCatatanPemantauan: RepositoriCatatanPemantauan) : ViewModel() {
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }

    val CatatanPemantauanHomeUiState: StateFlow<CatatanPemantauanHomeUiState> = repositoriCatatanPemantauan.getAllCatatanPemantauanStream().filterNotNull()
        .map { CatatanPemantauanHomeUiState(listCatatanPemantauan = it.toList()) }.stateIn(
            scope = viewModelScope, started = SharingStarted.WhileSubscribed(
                TIMEOUT_MILLIS
            ), initialValue = CatatanPemantauanHomeUiState()
        )


}

data class CatatanPemantauanHomeUiState(
    val listCatatanPemantauan: List<CatatanPemantauan> = listOf()
)