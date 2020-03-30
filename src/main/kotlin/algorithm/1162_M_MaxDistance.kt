package algorithm

/**
你现在手里有一份大小为 N x N 的『地图』（网格） grid，上面的每个『区域』（单元格）都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地，你知道距离陆地区域最远的海洋区域是是哪一个吗？请返回该海洋区域到离它最近的陆地区域的距离。

我们这里说的距离是『曼哈顿距离』（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个区域之间的距离是 |x0 - x1| + |y0 - y1| 。

如果我们的地图上只有陆地或者海洋，请返回 -1。

 

示例 1：



输入：[[1,0,1],[0,0,0],[1,0,1]]
输出：2
解释：
海洋区域 (1, 1) 和所有陆地区域之间的距离都达到最大，最大距离为 2。
示例 2：



输入：[[1,0,0],[0,0,0],[0,0,0]]
输出：4
解释：
海洋区域 (2, 2) 和所有陆地区域之间的距离都达到最大，最大距离为 4。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/as-far-from-land-as-possible
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun maxDistance(grid: Array<IntArray>): Int {
    val toSearch = mutableListOf<Distance>()
    var total = 0
    for (i in 0 until grid.size) {
        for (j in 0 until grid[i].size) {
            if (grid[i][j] == 1) {
                toSearch.add(Distance(Coordinate(i, j), 0))
            }
            total++
        }
    }
    if (toSearch.size == 0 || toSearch.size == total) {
        return -1
    }

    var maxDistance = 0
    val history = HashSet<Distance>()
    while (toSearch.isNotEmpty()) {
        val distance = toSearch.removeAt(0)
//        println(distance)
        history.add(distance)
        if (distance.distance > maxDistance) {
            maxDistance = distance.distance
        }
        expand(distance, grid, toSearch, history)
    }
    return maxDistance
}

fun expand(current: Distance, grid: Array<IntArray>, toSearch: MutableList<Distance>, history: Set<Distance>) {
    val list = mutableListOf<Distance>()
    val coordinate = current.coordinate
    if (coordinate.x > 0) {
        list.add(Distance(Coordinate(coordinate.x - 1, coordinate.y), current.distance + 1))
    }
    if (coordinate.x < grid.size - 1) {
        list.add(Distance(Coordinate(coordinate.x + 1, coordinate.y), current.distance + 1))
    }
    if (coordinate.y > 0) {
        list.add(Distance(Coordinate(coordinate.x, coordinate.y - 1), current.distance + 1))
    }
    if (coordinate.y < grid[coordinate.x].size - 1) {
        list.add(Distance(Coordinate(coordinate.x, coordinate.y + 1), current.distance + 1))
    }

    for (d in list) {
        if (!history.contains(d) && !toSearch.contains(d)) {
            toSearch.add(d)
        }
    }
}

class Distance(val coordinate: Coordinate, val distance: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Distance) return false

        if (coordinate != other.coordinate) return false

        return true
    }

    override fun hashCode(): Int {
        return coordinate.hashCode()
    }

    override fun toString(): String {
        return "${coordinate.x}:${coordinate.y} -> $distance "
    }
}

class Coordinate(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Coordinate) return false

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
    val grid = Array(3) {
        when (it) {
            0 -> intArrayOf(1, 0, 1)
            1 -> intArrayOf(0, 0, 0)
            else -> intArrayOf(1, 0, 1)
        }
    }
//    println(maxDistance(grid) == 2)

    val grid2 = Array(3) {
        when (it) {
            0 -> intArrayOf(1, 0, 0)
            1 -> intArrayOf(0, 0, 0)
            else -> intArrayOf(0, 0, 0)
        }
    }
//    println(maxDistance(grid2) == 4)

//    [[1,0,0,0,0,1,0,0,0,1],
//    [1,1,0,1,1,1,0,1,1,0],
//    [0,1,1,0,1,0,0,1,0,0],
//    [1,0,1,0,1,0,0,0,0,0],
//    [0,1,0,0,0,1,1,0,1,1],

//    [0,0,1,0,0,1,0,1,0,1],
//    [0,0,0,1,1,1,1,0,0,1],
//    [0,1,0,0,1,0,0,1,0,0],
//    [0,0,0,0,0,1,1,1,0,0],
//    [1,1,0,1,1,1,1,1,0,0]]

    val grid3 = Array(10) {
        when (it) {
            0 -> intArrayOf(1,0,0,0,0,1,0,0,0,1)
            1 -> intArrayOf(1,1,0,1,1,1,0,1,1,0)
            2 -> intArrayOf(0,1,1,0,1,0,0,1,0,0)
            3 -> intArrayOf(1,0,1,0,1,0,0,0,0,0)
            4 -> intArrayOf(0,1,0,0,0,1,1,0,1,1)

            5 -> intArrayOf(0,0,1,0,0,1,0,1,0,1)
            6 -> intArrayOf(0,0,0,1,1,1,1,0,0,1)
            7 -> intArrayOf(0,1,0,0,1,0,0,1,0,0)
            8 -> intArrayOf(0,0,0,0,0,1,1,1,0,0)
            else -> intArrayOf(1,1,0,1,1,1,1,1,0,0)
        }
    }
    println(maxDistance(grid3))
}