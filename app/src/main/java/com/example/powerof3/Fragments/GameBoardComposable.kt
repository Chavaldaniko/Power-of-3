package com.example.powerof3.Fragments

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.powerof3.gameLogic.GameState
import androidx.compose.foundation.border
import com.example.powerof3.ui.theme.GameColors

@Composable
fun GameBoardComposable(
    gameState: GameState,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val gridSize = gameState.board.size
        val totalSize = min(maxWidth, maxHeight) * 0.9f
        val cellSize = totalSize / gridSize

        Box(
            modifier = Modifier
                .size(totalSize)
                .background(GameColors.BoardBackground, RoundedCornerShape(8.dp))
                .border(2.dp, GameColors.BoardBorder, RoundedCornerShape(8.dp))
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                for (i in 1 until gridSize) {
                    val x = i * cellSize.toPx()
                    drawLine(
                        color = GameColors.GridLines,
                        start = androidx.compose.ui.geometry.Offset(x, 0f),
                        end = androidx.compose.ui.geometry.Offset(x, totalSize.toPx()),
                        strokeWidth = 2f
                    )
                }

                for (i in 1 until gridSize) {
                    val y = i * cellSize.toPx()
                    drawLine(
                        color = GameColors.GridLines,
                        start = androidx.compose.ui.geometry.Offset(0f, y),
                        end = androidx.compose.ui.geometry.Offset(totalSize.toPx(), y),
                        strokeWidth = 2f
                    )
                }
            }

            Column(modifier = Modifier.fillMaxSize()) {
                repeat(gridSize) { row ->
                    Row(modifier = Modifier.weight(1f)) {
                        repeat(gridSize) { col ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(Color.Transparent)
                            )
                        }
                    }
                }
            }

            for (row in gameState.board.indices) {
                for (col in gameState.board[row].indices) {
                    val value = gameState.board[row][col]
                    if (value != 0) {
                        TileComposable(
                            value = value,
                            row = row,
                            col = col,
                            cellSize = cellSize,
                        )
                    }
                }
            }
        }
    }
}