package algorithm

/**
给定整数数组 A，每次 move 操作将会选择任意 A[i]，并将其递增 1。

返回使 A 中的每个值都是唯一的最少操作次数。

示例 1:

输入：[1,2,2]
输出：1
解释：经过一次 move 操作，数组将变为 [1, 2, 3]。
示例 2:

输入：[3,2,1,2,1,7]
输出：6
解释：经过 6 次 move 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 move 操作是不能让数组的每个值唯一的。

 还是把问题转化为树搜索问题，使用广度优先算法查找问题
 */
fun minIncrementForUnique(arr: IntArray): Int {
    val toSearch = mutableListOf<Node>()
    val history = mutableListOf<Node>()
    toSearch.add(Node(arr, 0))
    while (toSearch.isNotEmpty()) {
        val node = toSearch.removeAt(0)
        if (isUnique(node.arr)) {
            return node.count
        }

        if (history.contains(node)) {
            continue
        }

        history.add(node)
        expand(node, toSearch, history)
    }
    return -1
}

fun expand(current: Node, toSearch: MutableList<Node>, history: MutableList<Node>) {
    val list = mutableListOf<Node>()
    val arr: IntArray = current.arr
    for (i in 0 until arr.size) {
        list.add(Node(IntArray(arr.size, {
            if (it == i) {
                arr[it] + 1
            } else {
                arr[it]
            }
        }), current.count + 1))
    }

    for (node in list) {
        if (history.contains(node)) {
            continue
        }

        toSearch.add(node)
    }
}

class Node(val arr: IntArray, var count: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false

        if (!arr.contentEquals(other.arr)) return false

        return true
    }

    override fun hashCode(): Int {
        return arr.contentHashCode()
    }
}

fun isUnique(arr: IntArray): Boolean {
    val len = arr.size
    for (i in 0 until len - 1) {
        for (j in i + 1 until len) {
            if (arr[i] == arr[j]) {
                return false
            }
        }
    }
    return true
}

fun main() {
    println(minIncrementForUnique(intArrayOf()))
    println(minIncrementForUnique(intArrayOf(1)))
    println(minIncrementForUnique(intArrayOf(1, 2)))
    println(minIncrementForUnique(intArrayOf(1, 2, 2)))
    println(minIncrementForUnique(intArrayOf(3, 2, 1, 2, 1, 7)))
}
