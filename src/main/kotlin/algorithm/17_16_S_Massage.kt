package algorithm

import kotlin.math.max

/**
一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。

注意：本题相对原题稍作改动

 

示例 1：

输入： [1,2,3,1]
输出： 4
解释： 选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
示例 2：

输入： [2,7,9,3,1]
输出： 12
解释： 选择 1 号预约、 3 号预约和 5 号预约，总时长 = 2 + 9 + 1 = 12。
示例 3：

输入： [2,1,4,5,3,1,1,3]
输出： 12
解释： 选择 1 号预约、 3 号预约、 5 号预约和 8 号预约，总时长 = 2 + 4 + 3 + 3 = 12。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/the-masseuse-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun massage(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }

    return massage(nums, nums.size - 1)
}

fun massage(nums: IntArray, index: Int): Int {
    return when (index) {
        0 -> nums[0]
        1 -> max(nums[0], nums[1])
        2 -> max(nums[0] + nums[2], nums[1])
        else -> max(massage(nums, index - 2) + nums[index], massage(nums, index - 1))
    }
}

fun main() {
    println((massage(intArrayOf(1, 2, 3, 1)) == 4).toString())
    println((massage(intArrayOf(2, 7, 9, 3, 1)) == 12).toString())
    println((massage(intArrayOf(2, 1, 4, 5, 3, 1, 1, 3)) == 12).toString())
//    println((massage(intArrayOf(114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240)) == 12).toString())
}