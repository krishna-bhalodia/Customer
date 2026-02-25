package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.harish.b2c.ui.theme.Shapes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: SheetState = rememberModalBottomSheetState(),
    content: @Composable ColumnScope.() -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = Color.White,
        shape = Shapes.extraLarge, // 32.dp radius as per Figma
        dragHandle = {
            // Replicates the "Rectangle" drag handle from Figma
            Box(
                modifier = Modifier
                    .padding(vertical = 12.dp)
                    .width(38.dp)
                    .height(3.dp)
                    .background(Color(0xFF7C858C), CircleShape)
            )
        },
        content = content
    )
}