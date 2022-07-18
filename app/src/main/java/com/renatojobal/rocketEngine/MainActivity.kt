package com.renatojobal.rocketEngine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.renatojobal.rocketEngine.ui.BottomNavigationItem
import com.renatojobal.rocketEngine.ui.HomeScreen
import com.renatojobal.rocketEngine.ui.offset.OffsetScreen
import com.renatojobal.rocketEngine.ui.theme.RocketEngineTheme


class MainActivity : ComponentActivity() {

    // View model
    private val sharedViewModel: SharedViewModel by viewModels()


    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RocketEngineAppCompose(sharedViewModel)
        }
    }

}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@Composable
fun RocketEngineAppCompose(sharedViewModel: SharedViewModel) {

    val darkTheme by sharedViewModel.darkThemeOn.observeAsState()

    RocketEngineTheme(darkTheme = (darkTheme ?: false)) {

        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()

        Scaffold()
        {
            RocketEngineNavHost(
                sharedViewModel = sharedViewModel,
                navHostController = navController
            )
        }


    }
}


@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun RocketEngineNavHost(
    sharedViewModel: SharedViewModel,
    navHostController: NavHostController,
) {

    val startDestination = "home"

    NavHost(
        navController = navHostController,
        modifier = Modifier.fillMaxSize(),
        startDestination = startDestination
    ) {

        composable(route = BottomNavigationItem.Home.route) {
            HomeScreen(sharedViewModel) { navHostController.navigate(BottomNavigationItem.Offset.route) }

        }

        composable(route = BottomNavigationItem.Offset.route) {
            OffsetScreen(sharedViewModel = sharedViewModel)

        }
    }


}

@Composable
fun AppTopBar(onClick: () -> Unit) {
    TopAppBar(
        elevation = 10.dp,
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (title, menu) = createRefs()
            IconButton(
                onClick = {
                    onClick()
                },
                modifier = Modifier
                    .constrainAs(menu) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                    }
            ) {
                Icon(Icons.Filled.Menu, "Menu")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    RocketEngineTheme {
        AppTopBar {}
    }

}




