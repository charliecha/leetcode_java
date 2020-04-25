package algorithm

import java.util.*

/**
给定一个 没有重复 数字的序列，返回其所有可能的全排列。

示例:

输入: [1,2,3]
输出:
[
[1,2,3],
[1,3,2],
[2,1,3],
[2,3,1],
[3,1,2],
[3,2,1]
]

搜索问题，问题转化为树形遍历问题

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutations
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun permute(nums: IntArray): List<List<Int>> {
    return permute(nums.size) {
        nums[it]
    }
}

fun permute(n: Int, value: (Int) -> Int = { it }): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    val toSearch = mutableListOf<MutableList<Int>>()
    for (i in 0 until n) {
        toSearch.add(mutableListOf(i))
    }

    val flag = IntArray(n)
    while (toSearch.isNotEmpty()) {
        val element = toSearch.removeAt(0)
        if (element.size == n) {
            result.add(element.map(value))
            continue
        }

        expand(toSearch, element, n, flag)
    }
    return result
}

private fun expand(
    toSearch: MutableList<MutableList<Int>>,
    element: List<Int>,
    n: Int,
    flag: IntArray
) {
    Arrays.fill(flag, 0)

    for (i in 0 until element.size) {
        flag[element[i]] = 1
    }

    for (i in 0 until n) {
        if (flag[i] == 0) {
            val newElement = mutableListOf<Int>().apply { addAll(element) }
            newElement.add(i)
            toSearch.add(newElement)
        }
    }
}

fun main() {
    println(permute(intArrayOf(1, 2, 3)))
    println(permute(3))
}