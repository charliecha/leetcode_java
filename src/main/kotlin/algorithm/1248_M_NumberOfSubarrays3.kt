package algorithm

import java.util.*

/**
给你一个整数数组 nums 和一个整数 k。

如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。

请返回这个数组中「优美子数组」的数目。

 

示例 1：

输入：nums = [1,1,2,1,1], k = 3
输出：2
解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
示例 2：

输入：nums = [2,4,6], k = 1
输出：0
解释：数列中不包含任何奇数，所以不存在优美子数组。
示例 3：

输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
输出：16

问题转化为以奇数分割的偶数数组，然后求解固定奇数个数的可能性。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun numberOfSubarrays3(nums: IntArray, k: Int): Int {
    val evenNums = mutableListOf<Int>()
    evenNums.add(0)
    for (num in nums) {
        if (num % 2 == 0) {
            evenNums[evenNums.size - 1]++
        } else {
            evenNums.add(0)
        }
    }

//    println(Arrays.toString(evenNums.toIntArray()))

    if (evenNums.size <= k) {
        return 0
    }

    var count = 0
    for (i in 0 until evenNums.size - k) {
        count += (evenNums[i] + 1) * (evenNums[i + k] + 1)
    }
    return count
}

fun main() {
    println(numberOfSubarrays3(intArrayOf(1, 1, 2, 1, 1), 3))
    println(numberOfSubarrays3(intArrayOf(2, 2, 2, 1, 2, 2, 1, 2, 2, 2), 2))
}