package com.example.powerof3.Fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.powerof3.gameLogic.GameState
import com.example.powerof3.gameLogic.GameClass
import com.example.powerof3.gameLogic.Moves
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class GameViewModel(val size: Int) : ViewModel() {
    private val game = GameClass(size)
    private val _gameState = MutableStateFlow<GameState>(
        GameState(Array(size) { Array(size) { 0 } }, 0, false)
    )
    val gameState: StateFlow<GameState> = _gameState

    init {
        game.startGame()
        _gameState.value = game.getState()
    }

    fun moveUp() {
        game.makeMove(Moves.UP)
        _gameState.value = game.getState()
    }

    fun moveDown() {
        game.makeMove(Moves.DOWN)
        _gameState.value = game.getState()
    }

    fun moveRight() {
        game.makeMove(Moves.RIGHT)
        _gameState.value = game.getState()
    }

    fun moveLeft() {
        game.makeMove(Moves.LEFT)
        _gameState.value = game.getState()
    }
}

//fun show(state: GameState)
//{
//    println(state.isGameOver)
//    println(state.scores)
//    val board = state.board
//    for (i in 0..board.size - 1) {
//        for (j in 0..board[0].size - 1) {
//            print("${board[i][j]} ")
//        }
//        println()
//    }
//    println()
//}