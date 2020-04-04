package algorithm

const val live: Int = 4
const val death = 2

fun gameOfLife(board: Array<IntArray>): Unit {
    for (row in 0 until board.size) {
        for (col in 0 until board[row].size) {
            nextState(board, row, col)
        }
    }

    swap(board)
}

private fun swap(board: Array<IntArray>) {
    for (row in 0 until board.size) {
        for (col in 0 until board[row].size) {
            if (board[row][col] / live != 0) {
                board[row][col] = 1
            } else {
                board[row][col] = 0
            }
        }
    }
}

private fun nextState(board: Array<IntArray>, row: Int, col: Int) {
    val v = board[row][col]
    val aroundLive = aroundLive(board, row, col)

    if (isLive(v)) {
        if (aroundLive < 2 || aroundLive > 3) {
            board[row][col] = board[row][col] + death
        } else {
            board[row][col] = board[row][col] + live
        }
    } else {
        if (aroundLive == 3) {
            board[row][col] = board[row][col] + live
        } else {
            board[row][col] = board[row][col] + death
        }
    }
}

private fun aroundLive(board: Array<IntArray>, row: Int, col: Int): Int {
    var count = 0
    if (row > 0) {
        if (col > 0) {
            if (isLive(board[row - 1][col - 1])) {
                count++
            }
        }

        if (col < board[row].size - 1) {
            if (isLive(board[row - 1][col + 1])) {
                count++
            }
        }

        if (isLive(board[row - 1][col])) {
            count++
        }
    }

    if (row < board.size - 1) {
        if (col > 0) {
            if (isLive(board[row + 1][col - 1])) {
                count++
            }
        }

        if (col < board[row].size - 1) {
            if (isLive(board[row + 1][col + 1])) {
                count++
            }
        }

        if (isLive(board[row + 1][col])) {
            count++
        }
    }

    if (col > 0) {
        if (isLive(board[row][col - 1])) {
            count++
        }
    }

    if (col < board[row].size - 1) {
        if (isLive(board[row][col + 1])) {
            count++
        }
    }

    return count
}

private fun isLive(v: Int) = v % 2 == 1

fun main() {

}