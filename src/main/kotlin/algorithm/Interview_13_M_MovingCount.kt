package algorithm

/**
地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

 

示例 1：

输入：m = 2, n = 3, k = 1
输出：3
示例 1：

输入：m = 3, n = 1, k = 0
输出：1
提示：

1 <= n,m <= 100
0 <= k <= 20

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 树遍历问题，广度优先遍历，并剪枝已走过的路径和不满足条件的路径
 */
fun movingCount(m: Int, n: Int, k: Int): Int {
    val toSearch = mutableSetOf<MCNode>()
    val history = mutableSetOf<MCNode>()
    toSearch.add(MCNode(0, 0))
    var count = 0
    while (toSearch.isNotEmpty()) {
        val node = toSearch.first()
        toSearch.remove(node)
        count++

        history.add(node)
        expand(node, m, n, k, toSearch, history)
    }

    return count
}

private fun expand(node: MCNode, m: Int, n: Int, k: Int, toSearch: MutableSet<MCNode>, history: MutableSet<MCNode>) {
    val x = node.x
    val y = node.y

    val list = mutableListOf<MCNode>()
    if (x > 0) {
        list.add(MCNode(x - 1, y))
    }
    if (y > 0) {
        list.add(MCNode(x, y - 1))
    }
    if (x < m - 1) {
        list.add(MCNode(x + 1, y))
    }
    if (y < n - 1) {
        list.add(MCNode(x, y + 1))
    }

    for (i in list) {
        if (history.contains(i) || toSearch.contains(i)) {
            continue
        }

        if (digitSum(i.x) + digitSum(i.y) > k) {
            history.add(i)
            continue
        }

        toSearch.add(i)
    }
}

private fun digitSum(n: Int): Int {
    var result = 0
    var i = n
    while (i != 0) {
        result += i % 10
        i /= 10
    }
    return result
}

class MCNode(val x: Int, val y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MCNode) return false

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
    println(movingCount(2, 3, 1))
    println(movingCount(3, 1, 0))
}