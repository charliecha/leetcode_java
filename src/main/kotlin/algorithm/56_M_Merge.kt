package algorithm

import kotlin.math.max
import kotlin.math.min

/**
给出一个区间的集合，请合并所有重叠的区间。

示例 1:

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
示例 2:

输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-intervals
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {


    val toMerge = mutableListOf<IntArray>()
    for (i in 0 until intervals.size) {
        toMerge.add(intervals[i])
    }

    var index = 0
    val result = mutableListOf<IntArray>()
    while (index < toMerge.size) {
        var current = toMerge[index]

        if (index < toMerge.size - 1) {
            var mergeCount = 0

            for (i in index + 1 until toMerge.size) {
                if (canMerge(current, toMerge[i])) {
                    mergeCount++
                    current = merge(current, toMerge[i])

                    // 移动位置
                    toMerge[i] = toMerge[index + mergeCount]
                }
            }

            if(mergeCount == 0) {
                result.add(current)
                index += 1
            } else {
                index += mergeCount
                toMerge[index] = current
            }
        } else {
            result.add(current)
            break
        }
    }
    return result.toTypedArray()
}

fun merge(a1: IntArray, a2: IntArray): IntArray {
    return intArrayOf(min(a1[0], a2[0]), max(a1[1], a2[1]))
}

fun canMerge(a1: IntArray, a2: IntArray): Boolean {
    return (a1[1] >= a2[0] && a1[1] <= a2[1]) || (a2[1] >= a1[0] && a2[1] <= a1[1])
}



