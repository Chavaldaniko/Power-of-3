package com.example.powerof3.gameLogic

enum class Moves {UP, DOWN, LEFT, RIGHT}

data class GameState(val board: Array<Array<Int>>, val scores: Int,
                     val isGameOver: Boolean)
class GameClass(private val boardSize: Int) {

    private val board = GameBoard(boardSize)
    private var scores = 0
    private var gameOver = true

    fun startGame() {
        gameOver = false
        scores = 0
        board.clear()
        board.addRandom()
        board.addRandom()
        board.addRandom()
    }

    fun makeMove(move: Moves) {
        val res = when(move) {
            Moves.UP -> board.moveUp()
            Moves.DOWN -> board.moveDown()
            Moves.RIGHT -> board.moveRight()
            Moves.LEFT -> board.moveLeft()
        }

        val (moved, score) = res
        scores += score
        if (!moved) {
            gameOver = !board.isAnyMovePossible()
        }
        else {
            board.addRandom()
        }
    }


    fun getState(): GameState {
        return GameState(
            board.getBoard().map { it.copyOf() }.toTypedArray(),
            scores,
            gameOver
        )
    }


}