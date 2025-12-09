package com.example.powerof3.Fragments

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import kotlin.math.abs
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import com.example.powerof3.gameLogic.GameState

@Composable
fun GameScreen(size: Int) {
    val viewModel = remember { GameViewModel(size = size) }

    val gameState: GameState by viewModel.gameState.observeAsState(GameState(
        Array(size) { Array(size) {0} }, 0, false
    ))

    GameBoardComposable(
        gameState = gameState,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val (x, y) = dragAmount
                    if (abs(x) > abs(y)) {
                        if (x > 0) {
                            viewModel.moveRight()
                        } else {
                            viewModel.moveLeft()
                        }
                    } else {
                        if (y > 0) {
                            viewModel.moveDown()
                        } else {
                            viewModel.moveUp()
                        }
                    }
                }
            }
    )
}
