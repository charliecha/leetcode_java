package algorithm

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
fun numIslands2(grid: Array<CharArray>): Int {
    var islands = 0
    while (true) {
        for (x in 0 until grid.size) {
            for (y in 0 until grid[x].size) {
                if (isLand(grid, x, y)) {
                    travelIsland(grid, x, y)
                    islands++
                    continue
                }
            }
        }

        break
    }
    return islands
}

private fun travelIsland(grid: Array<CharArray>, x: Int, y: Int) {
    resetLand(grid, x, y)

    if (x > 0 && isLand(grid, x - 1, y)) {
        travelIsland(grid, x - 1, y)
    }
    if (y > 0 && isLand(grid, x, y - 1)) {
        travelIsland(grid, x, y - 1)
    }
    if (x < grid.size - 1 && isLand(grid, x + 1, y)) {
        travelIsland(grid, x + 1, y)
    }
    if (y < grid[x].size - 1 && isLand(grid, x, y + 1)) {
        travelIsland(grid, x, y + 1)
    }
}

private fun isLand(grid: Array<CharArray>, x: Int, y: Int) = grid[x][y] == '1'

private fun resetLand(grid: Array<CharArray>, x: Int, y: Int) {
    grid[x][y] = '0'
}
