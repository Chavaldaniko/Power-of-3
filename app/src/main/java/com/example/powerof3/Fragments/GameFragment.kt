package com.example.powerof3.Fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChanged
import androidx.compose.ui.unit.dp
import com.example.powerof3.repository.RecordsRepository
import kotlin.math.abs

@Composable
fun GameScreen(
    size: Int,
    playerName: String,
    recordsRepository: RecordsRepository,
    onGameFinished: () -> Unit = {}
) {
    val viewModel = remember { GameViewModel(size, playerName, recordsRepository) }
    val gameState by viewModel.gameState.collectAsState()
    val isGameFinished by viewModel.isGameFinished.collectAsState()

    if (isGameFinished) {
        GameOverScreen(
            finalScore = gameState.scores,
            playerName = playerName,
            fieldSize = size,
            onExit = onGameFinished
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(com.example.powerof3.ui.theme.GameColors.ScreenBackground)
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

                                                if (abs(deltaX) > abs(deltaY)) {
                                                    if (deltaX > 0) {
                                                        viewModel.moveRight()
                                                    } else {
                                                        viewModel.moveLeft()
                                                    }
                                                } else {
                                                    if (deltaY > 0) {
                                                        viewModel.moveDown()
                                                    } else {
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
        ) {
            GameBoardComposable(
                gameState = gameState,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = "Счет: ${gameState.scores}",
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}