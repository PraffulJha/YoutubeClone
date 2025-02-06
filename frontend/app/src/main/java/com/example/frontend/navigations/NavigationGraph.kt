package com.example.frontend.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontend.screens.HomeScreen
import com.example.frontend.screens.LoginScreen
import com.example.frontend.screens.RegisterScreen
import com.example.frontend.screens.WelcomeScreen
import com.example.frontend.screens.UpdateAvatarScreen
import com.example.frontend.viewmodel.UserApiViewModel

@Composable
fun NavigationGraph(userApiViewModel: UserApiViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("home") { HomeScreen(navController) }
        composable("register") { RegisterScreen(navController,userApiViewModel) }
        composable("login") { LoginScreen(navController) }
        composable("welcome") { WelcomeScreen(navController) }
        composable("uploadavatar") { UpdateAvatarScreen(navController)  }
    }
}


