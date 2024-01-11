package com.example.uas_paw.ui.halaman

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas_paw.R
import com.example.uas_paw.navigasi.DestinasiNavigasi
import com.example.uas_paw.navigasi.GreenGuardianTopAppBar

object DestinasiPrev : DestinasiNavigasi {
    override val route = "prev"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrevShow(
    onGoToPlant: () -> Unit,
    onGoToStats: () -> Unit,
    onGoToMonitor: () -> Unit
) {
    Scaffold(topBar = {
        GreenGuardianTopAppBar(
            title = stringResource(id = DestinasiPrev.titleRes),
            canNavigateBack = false
        )
    }) { innerPadding ->
        PreviousScreen(
            onNavigatePlant = onGoToPlant,
            onNavigateStats = onGoToStats,
            onNavigateMonitor = onGoToMonitor,
            modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun PreviousScreen(
    onNavigatePlant: () -> Unit,
    onNavigateStats: () -> Unit,
    onNavigateMonitor: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Green Guardian",
            color = Color(0xFF51D567), // Mengubah warna teks menjadi hijau
            fontSize = 50.sp, // Mengubah ukuran font
            fontWeight = FontWeight.Bold // Membuat teks menjadi tebal
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Membuat button dengan warna dan ukuran yang sama
        val buttonModifier = Modifier
            .fillMaxWidth()
            .height(56.dp)

        Button(
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(Color(0xFF51D567)),
            onClick = onNavigatePlant
        ) {
            Text(text = "PLANTS", color = Color.White) // Mengubah warna teks menjadi putih
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(Color(0xFF51D567)),
            onClick = onNavigateStats
        ) {
            Text(text = "STATS", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = buttonModifier,
            colors = ButtonDefaults.buttonColors(Color(0xFF51D567)),
            onClick = onNavigateMonitor
        ) {
            Text(text = "MONITOR", color = Color.White)
        }
    }
}