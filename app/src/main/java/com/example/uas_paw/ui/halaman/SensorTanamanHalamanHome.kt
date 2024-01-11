package com.example.uas_paw.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_paw.R
import com.example.uas_paw.data.SensorTanaman
import com.example.uas_paw.model.PenyediaViewModel
import com.example.uas_paw.model.SensorTanamanHomeViewModel
import com.example.uas_paw.navigasi.DestinasiNavigasi
import com.example.uas_paw.navigasi.GreenGuardianTopAppBar

object DestinasiSensorTanamanHome : DestinasiNavigasi {
    override val route = "sensorTanaman_home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SensorTanamanHomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemHome: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: SensorTanamanHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(id = DestinasiSensorTanamanHome.titleRes),
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateToItemHome
            )
        }, floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.app_name)
                )
            }
        }
    ) { innerPadding ->
        val uiStateSensorTanaman by viewModel.SensorTanamanHomeUiState.collectAsState()
        BodyHome(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            itemSensorTanaman = uiStateSensorTanaman.listSensorTanaman,
            onSensorTanamanClick = onDetailClick

        )
    }
}

@Composable
fun BodyHome(
    modifier: Modifier,
    itemSensorTanaman: List<SensorTanaman>,
    onSensorTanamanClick: (Int) -> Unit = {}
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        if (itemSensorTanaman.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListSensorTanaman(
                itemSensorTanaman = itemSensorTanaman,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onSensorTanamanClick(it.id) }
            )
        }
    }
}

@Composable
fun ListSensorTanaman(
    itemSensorTanaman: List<SensorTanaman>,
    modifier: Modifier = Modifier,
    onItemClick: (SensorTanaman) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemSensorTanaman, key = { it.id }) { person ->
            DataSensorTanaman(
                sensorTanaman = person,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)).clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataSensorTanaman(
    sensorTanaman: SensorTanaman, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = sensorTanaman.locsensor,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = sensorTanaman.statustanaman,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}