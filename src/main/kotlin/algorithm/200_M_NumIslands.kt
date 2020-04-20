package algorithm

import java.util.*
import kotlin.math.abs

/**
给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地连接而成的。你可以假设网格的四个边均被水包围。

示例 1:

输入:
11110
11010
11000
00000

输出: 1
示例 2:

输入:
11000
11000
00100
00011

输出: 3

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/number-of-islands
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun numIslands(grid: Array<CharArray>): Int {
    val lands = mutableListOf<Land>()
    for (i in 0 until grid.size) {
        for (j in 0 until grid[i].size) {
            if ('1' == grid[i][j]) {
                lands.add(Land(i, j))
            }
        }
    }

    val islands = mutableListOf<Island>()
    while (lands.isNotEmpty()) {
        if (islands.isEmpty()) {
            islands.add(Island(lands.removeAt(0)))
        } else {
            val lastIsland = islands[islands.size - 1]
            var canMerge = false
            for (i in lands.size - 1 downTo 0) {
                if (lastIsland.merge(lands[i])) {
                    lands.removeAt(i)
                    canMerge = true
                }
            }

            if (!canMerge) {
                islands.add(Island(lands.removeAt(0)))
            }
        }
    }
    return islands.size
}

private class Land(val x: Int, val y :Int)

private class Island {
    val lands = mutableListOf<Land>()

    constructor(land: Land) {
        lands.add(land)
    }

    fun merge(land : Land) : Boolean {
        for (l in lands) {
            if (abs(l.x-land.x) + abs(l.y-land.y) == 1) {
                lands.add(land)
                return true
            }
        }
        return false
    }
}