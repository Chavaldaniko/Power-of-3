package com.example.powerof3.Fragments

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.powerof3.gameLogic.GameState

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun GameBoardComposable(
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    val gridSize = gameState.board.size
    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val cellSize = min(maxWidth, maxHeight) / gridSize
        Column(modifier = Modifier.fillMaxSize()) {
            repeat(gridSize) { row ->
                Row(modifier = Modifier.weight(1f)) {
                    repeat(gridSize) { col ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .background(Color(0xFFBBADA0), RoundedCornerShape(8.dp))
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                        )
                    }
                }
            }
        }
        for (row in gameState.board.indices) {
            for (col in gameState.board[row].indices) {
                TileComposable(
                    value = gameState.board[row][col],
                    row = row,
                    col = col,
                    cellSize = cellSize
                )
            }
        }
    }
}