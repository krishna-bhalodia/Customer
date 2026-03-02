package com.harish.b2c.presentation.products

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.harish.b2c.core.components.AppInput
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.core.components.CommonButton

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RaiseComplaintFormScreenPreview() {
    RaiseComplaintFormScreen(
        navController = NavController(LocalContext.current)
    )
}

@Composable
fun RaiseComplaintFormScreen(
    navController: NavController
) {
    var subjectText by remember { mutableStateOf("") }
    var descriptionText by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            CommonAppBar(
                title = "Raise Complaint",
                onBackClick = { navController.popBackStack() },
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 20.dp)
            )
        },
        bottomBar = {
            // Filled Bottom Button (Submit)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .navigationBarsPadding()
            ) {
                CommonButton(
                    text = "Submit",
                    onClick = { }
                )
            }
        }
    ) { paddingValues ->
        // Screen Body
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            // Subject Field
            // Subject Selection Field (Standard Input)
            AppInput(
                label = "What is your complaint related to?",
                value = subjectText,
                onValueChange = { subjectText = it },
                placeholder = "e.g., Service, Product, Payment, etc"
            )

            // Detail Description Field (Multiline Input)
            AppInput(
                label = "Tell us what went wrong in detail",
                value = descriptionText,
                onValueChange = { descriptionText = it },
                placeholder = "Describe your concern clearly...",
                isMultiline = true,
                componentHeight = 200.dp
            )
        }
    }
}
