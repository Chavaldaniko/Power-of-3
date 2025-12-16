package com.example.powerof3.Fragments

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    onGameFinished: () -> Unit = {},
    onViewRecords: () -> Unit = {}
) {
    val viewModel = remember { GameViewModel(size, playerName, recordsRepository) }
    val gameState by viewModel.gameState.collectAsState()

    if (gameState.isGameOver) {
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
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Счет: ${gameState.scores}",
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
            ) {
                IconButton(
                    onClick = onViewRecords,
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(12.dp)
                        )
                ) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(android.R.drawable.ic_menu_sort_by_size),
                        contentDescription = "Рекорды",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            ShowMainPicture()
        }
    }
}