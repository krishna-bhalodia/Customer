package com.harish.b2c.core.components

import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.harish.b2c.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "CommonInput - All Variants",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun CommonInputPreview() {

    Column(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        // 1️⃣ Empty
        CommonInput(
            value = "",
            onValueChange = {},
            placeholder = "Enter name"
        )

        // 2️⃣ Filled
        CommonInput(
            value = "Harish",
            onValueChange = {},
            placeholder = "Enter name"
        )

        // 3️⃣ Leading Icon
        CommonInput(
            value = "",
            onValueChange = {},
            placeholder = "Search",
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color(0xFF030304)
                )
            }
        )

        // 4️⃣ Trailing Icon
        CommonInput(
            value = "Search Text",
            onValueChange = {},
            placeholder = "Search",
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color(0xFF030304)
                )
            }
        )

        // 5️⃣ Both Icons
        CommonInput(
            value = "",
            onValueChange = {},
            placeholder = "Search",
            leadingIcon = {
                Icon(
                    painterResource(R.drawable.storefront),
                    contentDescription = null,
                    tint = Color(0xFF030304)
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    tint = Color(0xFF030304)
                )
            }
        )
    }
}

@Composable
fun CommonInput(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    leadingIcon: @Composable (() -> Unit)? = null, // Added leading icon slot
    trailingIcon: @Composable (() -> Unit)? = null
) {
    val textBlack = Color(0xFF030304)

    val textStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.signika_regular, FontWeight.Normal)),
        fontSize = 18.sp,
        lineHeight = 22.sp,
        color = textBlack
    )

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            // background: rgba(255, 255, 255, 0.3); border: 1px solid rgba(3, 3, 4, 0.16);
            .background(Color.White.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
            .border(1.dp, textBlack.copy(alpha = 0.16f), RoundedCornerShape(16.dp))
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Handle Leading Icon and Gap
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(16.dp)) // gap: 16px
        }

        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            cursorBrush = SolidColor(textBlack),
            modifier = Modifier.weight(1f),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = textStyle.copy(color = textBlack.copy(alpha = 0.3f)) // opacity: 0.3
                        )
                    }
                    innerTextField()
                }
            }
        )

        // Handle Trailing Icon and Gap
        if (trailingIcon != null) {
            Spacer(modifier = Modifier.width(16.dp)) // gap: 16px
            trailingIcon()
        }
    }
}