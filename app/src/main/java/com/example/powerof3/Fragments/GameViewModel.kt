package com.example.powerof3.Fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.powerof3.gameLogic.GameState
import com.example.powerof3.gameLogic.GameClass
import com.example.powerof3.gameLogic.Moves
import com.example.powerof3.repository.RecordsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.abs

class GameViewModel(
    val size: Int,
    private val playerName: String,
    private val recordsRepository: RecordsRepository
) : ViewModel() {
    private val game = GameClass(size)
    private val _gameState = MutableStateFlow<GameState>(
        GameState(Array(size) { Array(size) { 0 } }, 0, false)
    )
    private val _isGameFinished = MutableStateFlow(false)
    val gameState: StateFlow<GameState> = _gameState

    init {
        game.startGame()
        _gameState.value = game.getState()
    }

    fun moveUp() {
        if (_gameState.value.isGameOver) return

        game.makeMove(Moves.UP)
        val newState = game.getState()
        _gameState.value = newState

        if (newState.isGameOver) {
            finishGame()
        }
    }

    fun moveDown() {
        if (_gameState.value.isGameOver) return

        game.makeMove(Moves.DOWN)
        val newState = game.getState()
        _gameState.value = newState

        if (newState.isGameOver) {
            finishGame()
        }
    }

    fun moveRight() {
        if (_gameState.value.isGameOver) return

        game.makeMove(Moves.RIGHT)
        val newState = game.getState()
        _gameState.value = newState

        if (newState.isGameOver) {
            finishGame()
        }
    }

    fun moveLeft() {
        if (_gameState.value.isGameOver) return

        game.makeMove(Moves.LEFT)
        val newState = game.getState()
        _gameState.value = newState

        if (newState.isGameOver) {
            finishGame()
        }
    }

    fun moveProcess(deltaX: Float, deltaY: Float) {
        if (abs(deltaX) > abs(deltaY)) {
            if (deltaX > 0) {
                moveRight()
            } else {
                moveLeft()
            }
        } else {
            if (deltaY > 0) {
                moveDown()
            } else {
                moveUp()
            }
        }
    }

    private fun finishGame() {
        viewModelScope.launch {
            val finalScore = _gameState.value.scores
            recordsRepository.saveGameResult(playerName, finalScore, size)
            _isGameFinished.value = true
        }
    }
}