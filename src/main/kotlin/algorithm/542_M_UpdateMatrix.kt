package algorithm

/**

给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。

示例 1:
输入:

0 0 0
0 1 0
0 0 0
输出:

0 0 0
0 1 0
0 0 0
示例 2:
输入:

0 0 0
0 1 0
1 1 1
输出:

0 0 0
0 1 0
1 2 1
注意:

给定矩阵的元素个数不超过 10000。
给定矩阵中至少有一个元素是 0。
矩阵中的元素只在四个方向上相邻: 上、下、左、右。

 问题转化为树形广度优先遍历，同时结合剪枝
 */
fun updateMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val result = Array(matrix.size) {
        IntArray(matrix[it].size) {
            0
        }
    }

    val toSearch = mutableListOf<Position>()
    val history = mutableSetOf<Position>()

    for (row in 0 until matrix.size) {
        for (col in 0 until matrix[row].size) {
            if (matrix[row][col] == 0) {
                toSearch.add(Position(row, col, 0))
            }
        }
    }

    while (toSearch.isNotEmpty()) {
        val node = toSearch.removeAt(0)

        if (history.contains(node)) {
            continue
        }

        history.add(node)
        result[node.x][node.y] = node.distance

        expand(node, matrix.size, matrix[node.x].size, toSearch, history)
    }
    return result
}

private fun expand(position : Position, row : Int, col : Int, toSearch : MutableList<Position>, history : Set<Position>) {
    val x = position.x
    val y = position.y
    val list = mutableListOf<Position>()
    if (x > 0) {
        list.add(Position(x-1, y, position.distance + 1))
    }
    if (y > 0) {
        list.add(Position(x, y-1, position.distance + 1))
    }
    if (x < row - 1) {
        list.add(Position(x+1, y, position.distance + 1))
    }
    if (y < col - 1) {
        list.add(Position(x, y+1, position.distance + 1))
    }

    for (p in list) {
        if (!history.contains(p)) {
            toSearch.add(p)
        }
    }
}

private class Position(val x : Int, val y : Int, val distance : Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}

fun main() {

}