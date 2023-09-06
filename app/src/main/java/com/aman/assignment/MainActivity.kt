package com.aman.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aman.assignment.ui.explore_screen.ExploreScreen
import com.aman.assignment.ui.RefineScreen
import com.aman.assignment.ui.theme.AmanAssignmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmanAssignmentTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()


                    NavHost(navController = navController, startDestination = "explore") {

                        composable("explore") {
                            ExploreScreen(
                                onNavigateToRefine = {
                                    navController.navigate("refine_screen")
                                }
                            )
                        }

                        composable("refine_screen") {
                            RefineScreen(onNavigateUp = {navController.navigateUp()})
                        }

                    }

                }
            }
        }
    }
}


