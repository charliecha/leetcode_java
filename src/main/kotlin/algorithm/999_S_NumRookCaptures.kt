package algorithm

/**
在一个 8 x 8 的棋盘上，有一个白色车（rook）。也可能有空方块，白色的象（bishop）和黑色的卒（pawn）。它们分别以字符 “R”，“.”，“B” 和 “p” 给出。大写字符表示白棋，小写字符表示黑棋。

车按国际象棋中的规则移动：它选择四个基本方向中的一个（北，东，西和南），然后朝那个方向移动，直到它选择停止、到达棋盘的边缘或移动到同一方格来捕获该方格上颜色相反的卒。另外，车不能与其他友方（白色）象进入同一个方格。

返回车能够在一次移动中捕获到的卒的数量。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/available-captures-for-rook
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun numRookCaptures(board: Array<CharArray>): Int {
    var row = 0
    var col = 0
    for (r in 0 until board.size) {
        for (c in 0 until board[r].size) {
            if (board[r][c] == 'R') {
                row = r
                col = c
                break
            }
        }
    }

    var count = 0

    // top
    for (r in row - 1 downTo 0) {
        // println("$i")
        if (!board[r][col].isLetter()) {
            continue
        }

        if (board[r][col].isLowerCase()) {
            count++
        }
        break
    }

    println("$count")

    // down
    for (r in row + 1 until board.size) {
        if (!board[r][col].isLetter()) {
            continue
        }

        if (board[r][col].isLowerCase()) {
            count++
        }
        break
    }

    println("$count")

    // left
    for (c in col - 1 downTo 0) {
        if (!board[row][c].isLetter()) {
            continue
        }

        if (board[row][c].isLowerCase()) {
            count++
        }
        break
    }

    println("$count")

    // left
    for (c in col + 1 until board[row].size) {
        if (!board[row][c].isLetter()) {
            continue
        }

        if (board[row][c].isLowerCase()) {
            count++
        }
        break
    }

    println("$count")

    return count
}