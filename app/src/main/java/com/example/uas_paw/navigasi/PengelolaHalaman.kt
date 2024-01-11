package com.example.uas_paw.navigasi

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.uas_paw.R
import com.example.uas_paw.ui.halaman.CatatanPemantauanDestinasiEntry
import com.example.uas_paw.ui.halaman.CatatanPemantauanDetailDestination
import com.example.uas_paw.ui.halaman.CatatanPemantauanDetailScreen
import com.example.uas_paw.ui.halaman.CatatanPemantauanEditDestination
import com.example.uas_paw.ui.halaman.CatatanPemantauanEditScreen
import com.example.uas_paw.ui.halaman.CatatanPemantauanHomeScreen
import com.example.uas_paw.ui.halaman.DestinasiCatatanPemantauanHome
import com.example.uas_paw.ui.halaman.DestinasiPrev
import com.example.uas_paw.ui.halaman.DestinasiSensorTanamanHome
import com.example.uas_paw.ui.halaman.DestinasiTanamanHome
import com.example.uas_paw.ui.halaman.EntryCatatanPemantauanScreen
import com.example.uas_paw.ui.halaman.EntrySensorTanamanScreen
import com.example.uas_paw.ui.halaman.EntryTanamanScreen
import com.example.uas_paw.ui.halaman.PrevShow
import com.example.uas_paw.ui.halaman.SensorTanamanDestinasiEntry
import com.example.uas_paw.ui.halaman.SensorTanamanDetailDestination
import com.example.uas_paw.ui.halaman.SensorTanamanDetailScreen
import com.example.uas_paw.ui.halaman.SensorTanamanEditDestination
import com.example.uas_paw.ui.halaman.SensorTanamanEditScreen
import com.example.uas_paw.ui.halaman.SensorTanamanHomeScreen
import com.example.uas_paw.ui.halaman.TanamanDestinasiEntry
import com.example.uas_paw.ui.halaman.TanamanDetailDestination
import com.example.uas_paw.ui.halaman.TanamanDetailScreen
import com.example.uas_paw.ui.halaman.TanamanEditDestination
import com.example.uas_paw.ui.halaman.TanamanEditScreen
import com.example.uas_paw.ui.halaman.TanamanHomeScreen


sealed class Screen(val route: String) {
    object PreviousScreen : Screen("previous_screen")
}


@Composable
fun GreenApp(navController: NavHostController = rememberNavController()) {
    HostNavigasi(navController = navController)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreenGuardianTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier, scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = stringResource(
                            id = R.string.back
                        )
                    )
                }
            }
        }
    )
}


@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = DestinasiPrev.route,
        modifier = Modifier
    ) {
        composable(DestinasiPrev.route) {
            PrevShow(
                onGoToPlant = { navController.navigate(DestinasiTanamanHome.route) },
                onGoToStats = { navController.navigate(DestinasiSensorTanamanHome.route) },
                onGoToMonitor = { navController.navigate(DestinasiCatatanPemantauanHome.route) })
        }
        composable(DestinasiTanamanHome.route) {
            TanamanHomeScreen(
                navigateToItemEntry = { navController.navigate(TanamanDestinasiEntry.route) },
                onDetailClick = { itemId -> navController.navigate("${TanamanDetailDestination.route}/$itemId") },
                navigateToItemHome = {navController.popBackStack()})
        }
        composable(DestinasiSensorTanamanHome.route) {
            SensorTanamanHomeScreen(
                navigateToItemEntry = { navController.navigate(SensorTanamanDestinasiEntry.route) },
                onDetailClick = { itemId -> navController.navigate("${SensorTanamanDetailDestination.route}/$itemId") },
                navigateToItemHome = { navController.popBackStack() })
        }
        composable(DestinasiCatatanPemantauanHome.route) {
            CatatanPemantauanHomeScreen(
                navigateToItemEntry = { navController.navigate(CatatanPemantauanDestinasiEntry.route) },
                onDetailClick = { itemId -> navController.navigate("${CatatanPemantauanDetailDestination.route}/$itemId") },
                navigateToItemHome = { navController.popBackStack() })
        }


        composable(TanamanDestinasiEntry.route) {
            EntryTanamanScreen(
                navigateBack = { navController.popBackStack() },
                navigateBackTanaman = { navController.popBackStack() })
        }
        composable(SensorTanamanDestinasiEntry.route) {
            EntrySensorTanamanScreen(
                navigateBack = { navController.popBackStack() },
                navigateBackSensorTanaman = { navController.popBackStack() })
        }
        composable(CatatanPemantauanDestinasiEntry.route) {
            EntryCatatanPemantauanScreen(
                navigateBack = { navController.popBackStack() },
                navigateBackCatatanPemantauan = { navController.popBackStack() })
        }



        composable(
            TanamanDetailDestination.routeWithArgs,
            arguments = listOf(navArgument(TanamanDetailDestination.TanamanIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val TanamanId = backStackEntry.arguments?.getInt(TanamanDetailDestination.TanamanIdArg)
            TanamanId?.let {
                TanamanDetailScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditTanaman = { navController.navigate("${TanamanEditDestination.route}/$it") }
                )
            }
        }

        composable(
            TanamanEditDestination.routeWithArgs,
            arguments = listOf(navArgument(TanamanEditDestination.TanamanIdArg) {
                type = NavType.IntType
            })
        ) {
            TanamanEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }


    }

}