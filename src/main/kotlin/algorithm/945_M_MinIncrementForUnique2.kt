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
fun minIncrementForUnique2(arr: IntArray): Int {
    val history = mutableListOf<Int>()
    var count = 0
    for (v in arr) {
        if (!history.contains(v)) {
            history.add(v)
            continue
        }

        var newValue = v + 1
        count++
        while (history.contains(newValue)) {
            newValue++
            count++
        }
        history.add(newValue)
    }
    return count
}

fun main() {
//    println(minIncrementForUnique2(intArrayOf()))
//    println(minIncrementForUnique2(intArrayOf(1)))
//    println(minIncrementForUnique2(intArrayOf(1, 2)))
//    println(minIncrementForUnique2(intArrayOf(1, 2, 2)))
    println(minIncrementForUnique2(intArrayOf(3, 2, 1, 2, 1, 7)))
    println(minIncrementForUnique2(intArrayOf(7, 2, 7, 2, 1, 4, 3, 1, 4, 8)))
}
