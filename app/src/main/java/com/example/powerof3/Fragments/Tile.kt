package com.example.powerof3.Fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.powerof3.ui.theme.GameColors
import kotlin.math.min

@Composable
fun TileComposable(
    value: Int,
    row: Int,
    col: Int,
    cellSize: Dp,
) {
    if (value == 0) return

    val tileSize = cellSize * 0.9f
    val fontSizeSp = min(24f, cellSize.value / 3)

    Box(
        modifier = Modifier
            .size(tileSize)
            .offset(
                x = (col * cellSize.value + cellSize.value * 0.05f).dp,
                y = (row * cellSize.value + cellSize.value * 0.05f).dp
            )
            .background(
                color = GameColors.getTileColor(value),
                shape = RoundedCornerShape(6.dp)
            )
            .border(1.dp, GameColors.TileBorder, RoundedCornerShape(6.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value.toString(),
            style = TextStyle(
                fontSize = fontSizeSp.sp,
                fontWeight = FontWeight.Bold,
                color = GameColors.getTextColor(value)
            )
        )
    }
}