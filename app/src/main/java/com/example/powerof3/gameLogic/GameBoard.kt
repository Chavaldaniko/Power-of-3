package com.example.powerof3.gameLogic
import kotlin.random.Random

class GameBoard(private val size: Int) {

    private val board: Array<Array<Int>> = Array(size) { Array(size) {
        0
    } }

    fun getSize() : Int {
        return size
    }

    fun clear() {
        for (row in 0..size-1) {
            for (col in 0..size-1) {
                board[row][col] = 0
            }
        }
    }

    fun getElementValue(row: Int, col: Int) : Int {
        return board[row][col]
    }

    fun set(row: Int, col: Int, v: Int) {
        board[row][col] = v
    }

    fun moveLeft() : Pair<Boolean, Int> {
        var score = 0
        var isMoved = false
        for (i in 0..size-1) {
            val (rowMoved, rowScore) = leftMoveRow(i)
            isMoved = if (rowMoved) true else isMoved
            score += rowScore
        }

        return Pair(isMoved, score)
    }

    fun moveRight() : Pair<Boolean, Int> {
        var score = 0
        var isMoved = false
        for (i in 0..size-1) {
            val (rowMoved, rowScore) = rightMoveRow(i)
            isMoved = if (rowMoved) true else isMoved
            score += rowScore
        }

        return Pair(isMoved, score)
    }

    fun moveUp() : Pair<Boolean, Int> {
        var score = 0
        var isMoved = false
        for (i in 0..size-1) {
            val (colMoved, colScore) = upMoveCol(i)
            isMoved = if (colMoved) true else isMoved
            score += colScore
        }

        return Pair<Boolean, Int>(isMoved, score)
    }

    fun moveDown() : Pair<Boolean, Int> {
        var score = 0
        var isMoved = false
        for (i in 0..size-1) {
            val (colMoved, colScore) = downMoveCol(i)
            isMoved = if (colMoved) true else isMoved
            score += colScore
        }

        return Pair<Boolean, Int>(isMoved, score)
    }

    fun addRandom() {

        val emptyCells = mutableListOf<Pair<Int, Int>>()
        for (row in 0..size-1) {
            for (col in 0..size-1) {
                if (board[row][col] == 0) {
                    emptyCells.add(Pair<Int, Int>(row, col))
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return
        }

        val isOne = Random.nextInt(0, 3) <= 1;
        val idx = Random.nextInt(0, emptyCells.size);


        if (isOne) {
            board[emptyCells[idx].first][emptyCells[idx].second] = 1
        }
        else {
            board[emptyCells[idx].first][emptyCells[idx].second] = 3
        }


    }

    fun isAnyMovePossible() : Boolean {

        for (row in 0..size-1) {
            for (col in 0..size-1) {
                if (isCellMovable(row, col)) {
                    return true
                }
            }
        }

        return false

    }

    private fun isCellMovable(row: Int, col: Int) : Boolean {
        if (board[row][col] == 0) {
            return true
        }

        if (row >= 2 &&
            board[row][col] == board[row-1][col] &&
            board[row][col] == board[row-2][col]) {
            return true
        }

        if (row <= size - 3 &&
            board[row][col] == board[row+1][col] &&
            board[row][col] == board[row+2][col]) {
            return true
        }

        if (col >= 2 &&
            board[row][col] == board[row][col-1] &&
            board[row][col] == board[row][col-2]) {
            return true
        }

        if (col <= size - 3 &&
            board[row][col] == board[row][col+1] &&
            board[row][col] == board[row][col+2]) {
            return true
        }

        return false

    }

    private fun leftMoveRow(row: Int) : Pair<Boolean, Int> {

        var isMoved = false
        var score = 0
        val nonEmptyTiles = mutableListOf<Int>()

        for (i in 0..size-1) {
            if (board[row][i] != 0) {
                nonEmptyTiles.add(board[row][i])
            }
        }


        var curIdx = 0
        var i = 0

        while (i < nonEmptyTiles.size) {

            if (i < nonEmptyTiles.size - 2 &&
                nonEmptyTiles[i] == nonEmptyTiles[i+1] &&
                nonEmptyTiles[i+1] == nonEmptyTiles[i+2]) {

                board[row][curIdx] = nonEmptyTiles[i] * 3
                isMoved = true
                i += 3
                score += board[row][curIdx]
                curIdx++
            }

            else {
                if (board[row][curIdx] != nonEmptyTiles[i]) {
                    isMoved = true
                }
                board[row][curIdx] = nonEmptyTiles[i]
                i++
                curIdx++
            }

        }

        for (i in curIdx..size - 1) {
            board[row][i] = 0
        }
        return Pair(isMoved, score)
    }

    private fun rightMoveRow(row: Int) : Pair<Boolean, Int> {

        var score = 0
        var isMoved = false
        val nonEmptyTiles = mutableListOf<Int>()

        for (i in 0..size-1) {
            if (board[row][i] != 0) {
                nonEmptyTiles.add(board[row][i])
            }
        }


        var curIdx = size - 1
        var i = nonEmptyTiles.size - 1

        while (i >= 0) {

            if (i >= 2 &&
                nonEmptyTiles[i] == nonEmptyTiles[i-1] &&
                nonEmptyTiles[i-1] == nonEmptyTiles[i-2]) {

                board[row][curIdx] = nonEmptyTiles[i] * 3
                isMoved = true
                i -= 3
                score += board[row][curIdx]
                curIdx--
            }

            else {
                if (board[row][curIdx] != nonEmptyTiles[i]) {
                    isMoved = true
                }
                board[row][curIdx] = nonEmptyTiles[i]
                i--
                curIdx--
            }

        }

        for (i in 0..curIdx) {
            board[row][i] = 0
        }
        return Pair(isMoved, score)
    }

    private fun upMoveCol(col: Int) : Pair<Boolean, Int> {

        var score = 0
        var isMoved = false
        val nonEmptyTiles = mutableListOf<Int>()

        for (i in 0..size-1) {
            if (board[i][col] != 0) {
                nonEmptyTiles.add(board[i][col])
            }
        }


        var curIdx = 0
        var i = 0

        while (i < nonEmptyTiles.size) {

            if (i < nonEmptyTiles.size - 2 &&
                nonEmptyTiles[i] == nonEmptyTiles[i+1] &&
                nonEmptyTiles[i+1] == nonEmptyTiles[i+2]) {

                board[curIdx][col] = nonEmptyTiles[i] * 3
                isMoved = true
                i += 3
                score += board[curIdx][col]
                curIdx++
            }

            else {
                if (board[curIdx][col] != nonEmptyTiles[i]) {
                    isMoved = true
                }
                board[curIdx][col] = nonEmptyTiles[i]
                i++
                curIdx++
            }

        }

        for (i in curIdx..size - 1) {
            board[i][col] = 0
        }
        return Pair(isMoved, score)
    }

    private fun downMoveCol(col: Int) : Pair<Boolean, Int> {

        var score = 0
        var isMoved = false
        val nonEmptyTiles = mutableListOf<Int>()

        for (i in 0..size-1) {
            if (board[i][col] != 0) {
                nonEmptyTiles.add(board[i][col])
            }
        }


        var curIdx = size - 1
        var i = nonEmptyTiles.size - 1

        while (i >= 0) {

            if (i >= 2 &&
                nonEmptyTiles[i] == nonEmptyTiles[i-1] &&
                nonEmptyTiles[i-1] == nonEmptyTiles[i-2]) {

                board[curIdx][col] = nonEmptyTiles[i] * 3
                isMoved = true
                i -= 3
                score += board[curIdx][col]
                curIdx--
            }

            else {
                if (board[curIdx][col] != nonEmptyTiles[i]) {
                    isMoved = true
                }
                board[curIdx][col] = nonEmptyTiles[i]
                i--
                curIdx--
            }

        }

        for (i in 0..curIdx) {
            board[i][col] = 0
        }
        return Pair(isMoved, score)
    }


}
