package com.example.uas_pam.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas_pam.repositori.RepositoriCatatanPemantauan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class CatatanPemantauanDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriCatatanPemantauan: RepositoriCatatanPemantauan
) : ViewModel() {

    private val CatatanPemantauanID: Int = checkNotNull(savedStateHandle[DetailDestination.CatatanPemantauanIdArg])
    val uiState: StateFlow<ItemDetailUiState> =
        repositoriCatatanPemantauan.getCatatanPemantauanStream(CatatanPemantauanID).filterNotNull().map {
            ItemDetailUiState(detailCatatanPemantauan = it.toDetailCatatanPemantauan())
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = ItemDetailUiState()
        )

    suspend fun deleteItem() {
        repositoriCatatanPemantauan.deleteCatatanPemantauan(uiState.value.detailCatatanPemantauan.toCatatanPemantauan())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}
data class  ItemDetailUiState(
    val outOfStock: Boolean = true,
    val detailCatatanPemantauan: DetailCatatanPemantauan = DetailCatatanPemantauan()
)