package algorithm

import java.util.*

/**
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

示例 1：

输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]
示例 2：

输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]

位运算

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun singleNumbers(nums: IntArray): IntArray {
    var set = mutableSetOf<Int>()
    for (num in nums) {
        if (set.contains(num)) {
            set.remove(num)
        } else {
            set.add(num)
        }
    }
    return set.toIntArray()
}

fun singleNumbers2(nums: IntArray): IntArray {
    var xor = 0
    for (num in nums) {
        xor = xor.xor(num)
    }

    val low = xor.and(Int.MAX_VALUE - xor + 1)

    var r1 = 0
    for (num in nums) {
        if(low.and(num) == 0) {
            r1 = r1.xor(num)
        }
    }
    return intArrayOf(r1, r1.xor(xor))
}

fun main() {
//    println(Arrays.toString(singleNumbers(intArrayOf(1,2,5,2))))
    println(Arrays.toString(singleNumbers2(intArrayOf(1,2,5,2))))
}
