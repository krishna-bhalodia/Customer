package com.harish.b2c.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.core.components.CommonButton
import com.harish.b2c.core.components.CommonComplaintCard
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.TextBlack
import androidx.compose.ui.tooling.preview.Preview
import com.harish.b2c.core.components.CommonAppBar
import com.harish.b2c.ui.theme.B2CTheme // Change if your theme name is different

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RaiseComplaintScreenPreview() {
    GradientBackground {   // Replace with your actual theme wrapper if needed
        RaiseComplaintScreen(
            onBackClick = {},
            onCreateComplaintClick = {}
        )
    }
}

@Composable
fun RaiseComplaintScreen(
    onBackClick: () -> Unit,
    onCreateComplaintClick: () -> Unit
) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {CommonAppBar(title = "Raise Complaint", onBackClick = onBackClick , modifier = Modifier.statusBarsPadding().padding(top = 20.dp))} ,
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
                        onClick = onCreateComplaintClick
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
