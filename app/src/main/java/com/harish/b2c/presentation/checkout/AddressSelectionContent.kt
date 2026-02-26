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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.harish.b2c.R
import com.harish.b2c.core.components.CommonBottomSheet
import com.harish.b2c.core.components.GradientBackground
import com.harish.b2c.presentation.navigation.NavigationScreen
import com.harish.b2c.ui.theme.*

@Composable
fun AddressSelectionContent(navController: NavController, onDismiss: () -> Unit) {
    var selectedStore by remember { mutableStateOf("Store Name 1") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.large)
            .padding(bottom = Spacing.extraLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Select Delivery Address",
            style = AppTypography.titleLarge.copy(fontSize = 20.sp),
            color = TextBlack,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = Spacing.small)
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, TextBlack.copy(alpha = 0.1f)), Shapes.large)
                .padding(Spacing.medium),
            verticalArrangement = Arrangement.spacedBy(Spacing.mediumSmall)
        ) {
            AddressItem("Store Name 1", "S12, Kira Rd, Kampala", selectedStore == "Store Name 1") { selectedStore = "Store Name 1" }
            HorizontalDivider(modifier = Modifier.padding(vertical = Spacing.extraSmall), thickness = 0.5.dp, color = TextBlack.copy(alpha = 0.1f))
            AddressItem("Store Name 2", "U6, Ugan Rd, Kampala", selectedStore == "Store Name 2") { selectedStore = "Store Name 2" }
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        OutlinedButton(
            onClick = { navController.navigate(NavigationScreen.AddNewAddressScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = Shapes.large,
            border = BorderStroke(1.5.dp, BrandRed),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = BrandRed)
        ) {
            Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(Spacing.mediumLarge))
            Spacer(modifier = Modifier.width(Spacing.mediumSmall))
            Text("Add New Address", style = AppTypography.titleMedium.copy(fontWeight = FontWeight.SemiBold))
        }
    }
}

@Composable
fun AddressItem(title: String, address: String, isSelected: Boolean, onSelect: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onSelect() }, verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(32.dp)
            .background(MenuPurple.copy(alpha = 0.08f), CircleShape), contentAlignment = Alignment.Center) {
            Icon(painterResource(R.drawable.location), null, modifier = Modifier.size(18.dp), tint = MenuPurple)
        }
        Spacer(modifier = Modifier.width(Spacing.medium))
        Column(modifier = Modifier.weight(1f)) {
            Text(title, style = AppTypography.titleMedium.copy(fontSize = 14.sp), color = TextBlack)
            Text(address, style = AppTypography.bodyLarge.copy(fontSize = 12.sp, lineHeight = 16.sp), color = TextGrey)
        }
        Box(modifier = Modifier
            .size(20.dp)
            .border(
                BorderStroke(2.dp, if (isSelected) BrandRed else TextBlack.copy(alpha = 0.2f)),
                CircleShape
            ), contentAlignment = Alignment.Center) {
            if (isSelected) Box(modifier = Modifier
                .size(10.dp)
                .background(BrandRed, CircleShape))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddressSelectionPreview() {
    GradientBackground {
        AddressSelectionContent(navController = NavController(LocalContext.current), onDismiss = {})
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
                AddressSelectionContent(navController = NavController(LocalContext.current), onDismiss = { showSheet = false })
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