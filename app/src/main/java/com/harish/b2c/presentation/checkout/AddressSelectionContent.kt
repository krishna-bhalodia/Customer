package com.harish.b2c.presentation.checkout

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonBottomSheet
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.ui.theme.*

@Composable
fun AddressSelectionContent(
    onAddNewAddress: () -> Unit = {}
) {
    var selectedStore by remember { mutableStateOf("Store Name 1") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .padding(bottom = 34.dp), // Home Indicator padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Delivery Address",
            style = AppTypography.titleLarge.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            ),
            color = TextBlack,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Address List Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color(0x1A030304)), Shapes.large)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AddressItem(
                title = "Store Name 1",
                address = "S12, Kira Rd, Kampala Capital City, Central Region, 2121",
                isSelected = selectedStore == "Store Name 1",
                onSelect = { selectedStore = "Store Name 1" }
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp), thickness = 0.5.dp, color = TextBlack.copy(alpha = 0.1f))

            AddressItem(
                title = "Store Name 2",
                address = "U6, Ugan Rd, Kampala Capital City, Central Region, 2121",
                isSelected = selectedStore == "Store Name 2",
                onSelect = { selectedStore = "Store Name 2" }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Add New Address Button
        OutlinedButton(
            onClick = onAddNewAddress,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = Shapes.large,
            border = BorderStroke(1.5.dp, BrandRed),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandRed)
        ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(20.dp))
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Add New Address",
                style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}

@Composable
fun AddressItem(
    title: String,
    address: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelect() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon with Purple Background (0.08 alpha as per Figma)
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color(0x146949FF), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(R.drawable.location),
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = Color(0xFF6949FF)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = AppTypography.titleMedium.copy(fontSize = 14.sp), color = TextBlack)
            Text(address, style = AppTypography.bodyLarge.copy(fontSize = 12.sp, lineHeight = 16.sp), color = TextGrey)
        }

        // Custom Radio Button
        Box(
            modifier = Modifier
                .size(20.dp)
                .border(
                    BorderStroke(2.dp, if (isSelected) BrandRed else TextBlack.copy(alpha = 0.2f)),
                    CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .background(BrandRed, CircleShape)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressSelectionPreview() {
    GradientBackground {
        AddressSelectionContent()
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressSelectionScreen() {
    var showSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Box(modifier = Modifier.fillMaxSize()) {
        // Main Content that gets blurred
        Column(
            modifier = Modifier
                .fillMaxSize()
                .blur(if (showSheet) 12.dp else 0.dp)
                .padding(16.dp)
        ) {
            Button(onClick = { showSheet = true }) {
                Text("Change Delivery Address")
            }
        }

        if (showSheet) {
            CommonBottomSheet(
                sheetState = sheetState,
                onDismissRequest = { showSheet = false }
            ) {
                AddressSelectionContent(onAddNewAddress = { /* Navigate */ })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FullScreenBlurPreview() {
    GradientBackground {
        AddressSelectionScreen()
    }
}