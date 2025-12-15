package com.example.powerof3.ui.theme

import androidx.compose.ui.graphics.Color

object GameColors {
    val ScreenBackground = Color(0xFFFAF8EF)

    val BoardBackground = Color(0xFFBBADA0)
    val GridLines = Color(0xFF8F7A66)
    val BoardBorder = Color(0xFF8F7A66)

    val TileBorder = Color(0xFF8F7A66)

    val Tile1 = Color(0xFFE8F4FD)
    val Tile3 = Color(0xFFD4E8FA)
    val Tile9 = Color(0xFFA9D1F5)
    val Tile27 = Color(0xFF7EBAF0)
    val Tile81 = Color(0xFF53A3EB)
    val Tile243 = Color(0xFF288CE6)
    val Tile729 = Color(0xFF1C70B8)
    val Tile2187 = Color(0xFF15548A)
    val Tile6561 = Color(0xFF0E385C)
    val Tile19683 = Color(0xFF071C2E)
    val Tile59049 = Color(0xFF04111C)
    val Tile177147 = Color(0xFF02090E)
    val Tile531441 = Color(0xFF010507)
    val Tile1594323 = Color(0xFF000304)
    val Tile4782969 = Color(0xFF000202)
    val Tile14348907 = Color(0xFF000101)
    val Tile43046721 = Color(0xFF000000)
    val Tile129140163 = Color(0xFF000000)
    val Tile387420489 = Color(0xFF000000)
    val Tile1162261467 = Color(0xFF000000)
    val TileDefault = Color(0xFFEDC22E)

    val TextLight = Color.Black
    val TextDark = Color.White

    fun getTileColor(value: Int): Color {
        return when (value) {
            1 -> Tile1
            3 -> Tile3
            9 -> Tile9
            27 -> Tile27
            81 -> Tile81
            243 -> Tile243
            729 -> Tile729
            2187 -> Tile2187
            6561 -> Tile6561
            19683 -> Tile19683
            59049 -> Tile59049
            177147 -> Tile177147
            531441 -> Tile531441
            1594323 -> Tile1594323
            4782969 -> Tile4782969
            14348907 -> Tile14348907
            43046721 -> Tile43046721
            129140163 -> Tile129140163
            387420489 -> Tile387420489
            1162261467 -> Tile1162261467
            else -> TileDefault
        }
    }

    fun getTextColor(value: Int): Color {
        return if (value <= 81) TextLight else TextDark
    }
}