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
更正，树形查找效率太低，改为使用二分查找实现，历史记录为一个有序的数组
 */
fun minIncrementForUnique3(arr: IntArray): Int {
    val history = mutableListOf<Int>()
    var count = 0

    for (v in arr) {
        val index = history.binarySearch(v)
        if (index < 0) {
            history.add(-index - 1, v)
            continue
        }

        var newValue = history[history.size - 1] + 1
        var newIndex = history.size
        for (i in index + 1 until history.size) {
            if (v + i - index != history[i]) {
                newValue = v + i - index
                newIndex = i
                break;
            }
        }


        count += newValue - v
        history.add(newIndex, newValue)
    }
    return count
}

fun main() {
    println(minIncrementForUnique2(intArrayOf()))
    println(minIncrementForUnique2(intArrayOf(1)))
    println(minIncrementForUnique2(intArrayOf(1, 2)))
    println(minIncrementForUnique2(intArrayOf(1, 2, 2)))
    println(minIncrementForUnique3(intArrayOf(3, 2, 1, 2, 1, 7)))
    println(minIncrementForUnique3(intArrayOf(7, 2, 7, 2, 1, 4, 3, 1, 4, 8)))
    println(minIncrementForUnique3(intArrayOf(4, 4, 7, 5, 1, 9, 4, 7, 3, 8)))
    println(minIncrementForUnique3(intArrayOf(3, 2, 1, 2, 1, 7)))
    println(minIncrementForUnique3(intArrayOf(7, 2, 7, 2, 1, 4, 3, 1, 4, 8)))
}
