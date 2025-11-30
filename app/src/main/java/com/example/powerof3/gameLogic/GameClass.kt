package com.example.powerof3.gameLogic

enum class Moves {UP, DOWN, LEFT, RIGHT}


class GameClass(private val boardSize: Int) {

    private val board = GameBoard(boardSize)
    private var scores = 0
    private var gameOver = false

    fun startGame() {
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

    fun isGameOver() : Boolean {
        return gameOver
    }

    fun getScores() : Int {
        return scores
    }
    fun getBoardSize() : Int {
        return boardSize
    }

    fun getCellValue(row: Int, col: Int) : Int {

        return board.getElementValue(row, col)

    }

}

fun printGame(gameClass: GameClass) {

    println("Scores : ${gameClass.getScores()}")
    for (row in 0..gameClass.getBoardSize() - 1) {
        for (col in 0..gameClass.getBoardSize() - 1) {
            print(String.format("% 10d", gameClass.getCellValue(row, col)))
        }
        println()
    }
    println()
}

fun main() {

    val g = GameClass(2)
    g.startGame()
    while (!g.isGameOver()) {

        printGame(g)
        print("Next move(WASD): ")
        val move = readln()
        when (move) {
            "w" -> g.makeMove(Moves.UP)
            "a" -> g.makeMove(Moves.LEFT)
            "s" -> g.makeMove(Moves.DOWN)
            "d" -> g.makeMove(Moves.RIGHT)
            "e" -> break
        }

    }
    print("GameOver");


}