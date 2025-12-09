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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TileComposable(
    value: Int,
    row: Int,
    col: Int,
    cellSize: Dp
) {
    if (value == 0) return
    Box(
        modifier = Modifier
            .size(cellSize)
            .offset(
                x = (col * cellSize.value).dp,
                y = (row * cellSize.value).dp
            )
            .background(
                color = when (value) {
                    1 -> Color(0xFFE8F4FD)
                    3 -> Color(0xFFD4E8FA)
                    9 -> Color(0xFFA9D1F5)
                    27 -> Color(0xFF7EBAF0)
                    81 -> Color(0xFF53A3EB)
                    243 -> Color(0xFF288CE6)
                    729 -> Color(0xFF1C70B8)
                    2187 -> Color(0xFF15548A)
                    6561 -> Color(0xFF0E385C)
                    19683 -> Color(0xFF071C2E)
                    59049 -> Color(0xFF04111C)
                    177147 -> Color(0xFF02090E)
                    531441 -> Color(0xFF010507)
                    1594323 -> Color(0xFF000304)
                    4782969 -> Color(0xFF000202)
                    14348907 -> Color(0xFF000101)
                    43046721 -> Color(0xFF000000)
                    129140163 -> Color(0xFF000000)
                    387420489 -> Color(0xFF000000)
                    1162261467 -> Color(0xFF000000)
                    else -> Color(0xFFEDC22E)
                },
                shape = RoundedCornerShape(8.dp)
            )
            .border(2.dp, Color.Gray, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value.toString(),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color =  Color.Black
            )
        )
    }
}