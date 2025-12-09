package com.example.powerof3.Fragments

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.example.powerof3.gameLogic.GameState
import kotlinx.coroutines.CoroutineScope
import kotlin.math.abs
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.input.pointer.positionChanged
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext
import kotlin.math.atan2
import kotlin.math.sqrt

@Composable
fun GameScreen(size: Int) {

    val viewModel = remember { GameViewModel(size = size) }
    val gameState by viewModel.gameState.collectAsState()


    val density = LocalDensity.current
    val threshold = remember(density) { with(density) { 10.dp.toPx() } }

    GameBoardComposable(
        gameState = gameState,
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                var startX = 0f
                var startY = 0f
                var lastSwipeTime = 0L

                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        val firstChange = event.changes.firstOrNull()

                        if (firstChange != null) {
                            when (firstChange.positionChanged()) {
                                true -> {
                                    val currentX = firstChange.position.x
                                    val currentY = firstChange.position.y

                                    val deltaX = currentX - startX
                                    val deltaY = currentY - startY

                                    val currentTime = System.currentTimeMillis()

                                    if (currentTime - lastSwipeTime > 100) {
                                        val minDistance = 400f

                                        if (abs(deltaX) > minDistance || abs(deltaY) > minDistance) {
                                            lastSwipeTime = currentTime

                                            println("TOUCH MOVE: dx=$deltaX, dy=$deltaY")

                                            if (abs(deltaX) > abs(deltaY)) {
                                                if (deltaX > 0) {
                                                    println("→ RIGHT")
                                                    viewModel.moveRight()
                                                } else {
                                                    println("← LEFT")
                                                    viewModel.moveLeft()
                                                }
                                            } else {
                                                if (deltaY > 0) {
                                                    println("↓ DOWN")
                                                    viewModel.moveDown()
                                                } else {
                                                    println("↑ UP")
                                                    viewModel.moveUp()
                                                }
                                            }

                                            startX = currentX
                                            startY = currentY
                                        }
                                    }
                                }
                                false -> {
                                    if (firstChange.pressed) {
                                        startX = firstChange.position.x
                                        startY = firstChange.position.y
                                        println("TOUCH START: ($startX, $startY)")
                                    } else {
                                        println("TOUCH END")
                                    }
                                }
                            }

                            if (firstChange.pressed) {
                                firstChange.consume()
                            }
                        }
                    }
                }
            }
    )
}