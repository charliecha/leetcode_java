package algorithm

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

暴力求解

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/count-number-of-nice-subarrays
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun numberOfSubarrays(nums: IntArray, k: Int): Int {
    var count = 0
    for (i in 0 until nums.size) {
        var odd = 0
        for (j in i until nums.size) {
            if (nums[j] % 2 == 1) {
                odd++
            }

            if (odd > k) {
                break
            }

            if (odd == k) {
                count++
            }
        }
    }
    return count
}

fun main() {
    println(numberOfSubarrays(intArrayOf(2,2,2,1,2,2,1,2,2,2), 2))
}