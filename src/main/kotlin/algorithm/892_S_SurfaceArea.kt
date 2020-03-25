package algorithm

import kotlin.math.min

/**
在 N * N 的网格上，我们放置一些 1 * 1 * 1  的立方体。

每个值 v = grid[i][j] 表示 v 个正方体叠放在对应单元格 (i, j) 上。

请你返回最终形体的表面积。

 

示例 1：

输入：[[2]]
输出：10
示例 2：

输入：[[1,2],[3,4]]
输出：34
示例 3：

输入：[[1,0],[0,2]]
输出：16
示例 4：

输入：[[1,1,1],[1,0,1],[1,1,1]]
输出：32
示例 5：

输入：[[2,2,2],[2,1,2],[2,2,2]]
输出：46
 

提示：

1 <= N <= 50
0 <= grid[i][j] <= 50

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/surface-area-of-3d-shapes
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun surfaceArea(grid: Array<IntArray>): Int {
    var count = 0
    for (row in 0 until grid.size) {
        for (col in 0 until grid[row].size) {
            count += if (grid[row][col] > 0) {
                4 * grid[row][col] + 2
            } else {
                0
            }
            if (col > 0 && col < grid[row].size) {
                count -= min(grid[row][col], grid[row][col - 1]).shl(1)
            }

            if (row > 0 && row < grid.size) {
                count -= min(grid[row][col], grid[row - 1][col]).shl(1)
            }
        }
    }
    return count
}

fun main() {
    println(surfaceArea(Array(1) { intArrayOf(2) }) == 10)
    println(surfaceArea(Array(2) {
        if (it == 0) {
            intArrayOf(1, 2)
        } else {
            intArrayOf(3, 4)
        }
    }) == 34)
    println(surfaceArea(Array(3) {
        when (it) {
            0 -> intArrayOf(1, 1, 1)
            1 -> intArrayOf(1, 0, 1)
            else -> intArrayOf(1, 1, 1)
        }
    }) == 32)
    println(surfaceArea(Array(3) {
        when (it) {
            0 -> intArrayOf(2, 2, 2)
            1 -> intArrayOf(2, 1, 2)
            else -> intArrayOf(2, 2, 2)
        }
    }) == 46)
}

