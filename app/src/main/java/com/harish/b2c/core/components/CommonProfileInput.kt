package com.harish.b2c.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.ui.theme.AppTypography
import com.harish.b2c.ui.theme.BrandRed
import com.harish.b2c.ui.theme.Signika
import com.harish.b2c.ui.theme.TextBlack
import com.harish.b2c.ui.theme.White

@Composable
fun CommonProfileInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: Int,
    isEditable: Boolean = true,
    trailingActionText: String? = null,
    onTrailingActionClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = label,
            fontFamily = Signika,
            fontSize = 18.sp,
            color = TextBlack
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = if (isEditable) White.copy(alpha = 0.3f) else TextBlack.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(16.dp)
                )
                .border(1.dp, TextBlack.copy(alpha = 0.16f), RoundedCornerShape(16.dp))
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = TextBlack,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                enabled = isEditable,
                textStyle = AppTypography.bodyLarge.copy(fontSize = 18.sp, color = TextBlack),
                cursorBrush = SolidColor(TextBlack),
                modifier = Modifier.weight(1f),
                singleLine = true,
                decorationBox = { innerTextField ->
                    Box(contentAlignment = Alignment.CenterStart) {
                        if (value.isEmpty()) {
                            Text(
                                text = "Enter $label",
                                style = AppTypography.bodyLarge.copy(
                                    fontSize = 18.sp,
                                    color = TextBlack.copy(alpha = 0.3f)
                                )
                            )
                        }
                        innerTextField()
                    }
                }
            )

            if (trailingActionText != null) {
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = trailingActionText,
                    fontFamily = Signika,
                    fontSize = 18.sp,
                    color = BrandRed,
                    modifier = Modifier.clickable { onTrailingActionClick() }
                )
            }
        }
    }
}