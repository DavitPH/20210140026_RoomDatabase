package com.example.roomdatabase.Navigasi

import android.icu.text.CaseMap.Title
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
import com.example.roomdatabase.R
import com.example.roomdatabase.ui.theme.DestinasiEntry
import com.example.roomdatabase.ui.theme.DestinasiHome
import com.example.roomdatabase.ui.theme.DetailsScreen
import com.example.roomdatabase.ui.theme.EntrySiswaScreen
import com.example.roomdatabase.ui.theme.HalamanDetail
import com.example.roomdatabase.ui.theme.HomeScreen
import com.example.roomdatabase.ui.theme.ItemEditDestination
import com.example.roomdatabase.ui.theme.ItemEditScreen


@Composable
fun SiswaApp(navController: NavHostController = rememberNavController()){
    HostNavigasi(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    navigateUp: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        title = { Text(title) },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )

}
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier : Modifier = Modifier) {
    NavHost(navController = navController, startDestination = DestinasiHome.route,  modifier = Modifier ){
        composable(DestinasiHome.route){
            HomeScreen(navigateToitemEntry = { navController.navigate(DestinasiEntry.route) })
        }

        composable(DestinasiEntry.route){
            EntrySiswaScreen(navigateBack = { navController.popBackStack() })
        }

        composable(
            HalamanDetail.routeWithArgs,
            arguments = listOf(navArgument(HalamanDetail.siswaIdArg) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(HalamanDetail.siswaIdArg)
            itemId?.let {
                DetailsScreen(
                    navigateBack = { navController.popBackStack() },
                    navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") }
                )
            }
        }

        composable(
            ItemEditDestination.routeWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            ItemEditScreen(navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }

    }
}
