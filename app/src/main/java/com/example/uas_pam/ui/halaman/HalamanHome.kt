package com.example.uas_pam.ui.halaman

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uas_pam.Greeting
import com.example.uas_pam.data.Tanaman
import com.example.uas_pam.model.HomeViewModel
import com.example.uas_pam.model.PenyediaViewModel
import com.example.uas_pam.navigasi.DestinasiNavigasi
import com.example.uas_pam.navigasi.GreenGuardianTopAppBar
import com.example.uas_pam.ui.theme.UAS_PAWTheme
import com.example.uas_paw.R


object DestinasiHome : DestinasiNavigasi {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToPlants: () -> Unit,
    navigateToStats: () -> Unit,
    navigateToHistory: () -> Unit,
    onDetailClick: (Int) -> Unit = {},
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            GreenGuardianTopAppBar(
                title = stringResource(id = DestinasiHome.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onLogoutClick = onLogoutClick
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large))
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.entry_tanaman)
                )
            }
        }
    ) { innerPadding ->
        val uiStateTanaman by viewModel.HomeUiState.collectAsState()
        BodyHome(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            itemTanaman = uiStateTanaman.listTanaman,
            onSiswaClick = onDetailClick,
            navigateToPlants = navigateToPlants,
            navigateToStats = navigateToStats,
            navigateToHistory = navigateToHistory
        )
    }
}

@Composable
fun BodyHome(
    modifier: Modifier,
    itemTanaman: List<Tanaman>,
    onSiswaClick: (Int) -> Unit = {},
    navigateToPlants: () -> Unit,
    navigateToStats: () -> Unit,
    navigateToHistory: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier) {
        // Tambahkan button "PLANTS"
        Button(onClick = { navigateToPlants() }) {
            Text("PLANTS", style = MaterialTheme.typography.displayLarge)
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

        // Tambahkan button "STATS"
        Text("STATS", style = MaterialTheme.typography.displaySmall)
    }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

        // Tambahkan button "HISTORY"
        Button(onClick = { navigateToHistory() }) {
            Text("HISTORY", style = MaterialTheme.typography.displaySmall)
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.padding_small)))

        if (itemTanaman.isEmpty()) {
            Text(
                text = stringResource(R.string.deskripsi_no_item),
                textAlign = TextAlign.Center, style = MaterialTheme.typography.titleLarge
            )
        } else {
            ListTanaman(
                itemTanaman = itemTanaman,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small)),
                onItemClick = { onSiswaClick(it.id) }
            )
        }
    }

@Composable
fun ListTanaman(
    itemTanaman: List<Tanaman>,
    modifier: Modifier = Modifier,
    onItemClick: (Tanaman) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        items(items = itemTanaman, key = { it.id }) { person ->
            DataTanaman(
                tanaman = person,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)).clickable { onItemClick(person) }
            )
        }
    }
}

@Composable
fun DataTanaman(
    tanaman: Tanaman, modifier: Modifier = Modifier
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
                    text = tanaman.nmtanaman,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null
                )
                Text(
                    text = tanaman.loctanaman,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = tanaman.jnstanaman,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}
