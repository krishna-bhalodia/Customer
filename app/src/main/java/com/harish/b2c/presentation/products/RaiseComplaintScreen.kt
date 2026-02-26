package com.harish.b2c.presentation.products

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonComplaintCard
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.BrandRed
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.presentation.navigation.NavigationScreen

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RaiseComplaintScreenPreview() {
    GradientBackground {   // Replace with your actual theme wrapper if needed
        RaiseComplaintScreen(
            navController = NavController(LocalContext.current)
        )
    }
}

@Composable
fun RaiseComplaintScreen(
    navController: NavController
) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {CommonAppBar(title = "Raise Complaint", onBackClick = {  navController.popBackStack()  } , modifier = Modifier
                .statusBarsPadding()
                .padding(top = 20.dp))} ,
            bottomBar = {
                // Outlined Bottom Button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .navigationBarsPadding()
                ) {
                    CommonButton(
                        text = "Create Complaint",
                        isOutlined = true,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Create",
                                tint = BrandRed,
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        onClick = {
                            navController.navigate(NavigationScreen.RaiseComplaintForm.route)
                         }
                    )
                }
            }
        ) { paddingValues ->
            // Screen Body
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Dummy Item based on Figma design
                item {
                    CommonComplaintCard(
                        complaintId = "#RCO00300012",
                        title = "Lorem ipsum dolor sit amet",
                        status = "In-progress",
                        onClick = { /* Handle Item Click */ }
                    )
                }
            }
        }
    }
