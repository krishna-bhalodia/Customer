package com.harish.b2c.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.harish.b2c.R
import com.harish.b2c.presentation.navigation.NavigationScreen


@Composable
fun SplashScreen(navController: NavHostController, viewModel: SplashViewModel) {
    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { screen ->
            navController.navigate(screen.route) {
                popUpTo(NavigationScreen.SplashScreen.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize().navigationBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_screen),
            contentDescription = "City Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alignment = Alignment.BottomCenter
        )
    }
}
