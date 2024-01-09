package com.example.uas_pam.ui.halaman

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.model.PenyediaViewModel
import com.example.uas_pam.model.TanamanEditViewModel
import com.example.uas_pam.navigasi.DestinasiNavigasi
import com.example.uas_pam.navigasi.GreenGuardianTopAppBar
import com.example.uas_paw.R
import kotlinx.coroutines.launch

object ItemEditDestination : DestinasiNavigasi {
    override val route = "item_edit"
    override val titleRes = R.string.edit_tanaman
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TanamanEditViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(ItemEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        EntryTanamanBody(
            uiStateTanaman = viewModel.TanamanUiState,
            onTanamanValueChange = viewModel::updateUiState,
            onSaveClick = {
                // Note: If the user rotates the screen very fast, the operation may get cancelled
                // and the item may not be updated in the Database. This is because when config
                // change occurs, the Activity will be recreated and the rememberCoroutineScope will
                // be cancelled - since the scope is bound to composition.
                coroutineScope.launch {
                    viewModel.updateTanaman()
                    navigateBack()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}