package algorithm

/**
假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

输入: nums = [4,5,6,7,0,1,2], target = 0
输出: 4
示例 2:

输入: nums = [4,5,6,7,0,1,2], target = 3
输出: -1

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun search(nums: IntArray, target: Int): Int {
    return search(nums, target, 0, nums.size - 1)
}

fun search(nums: IntArray, target: Int, start: Int, end: Int): Int {
    if (start > end) {
        return -1
    }

    if (nums[start] == target) {
        return start
    }

    if (nums[end] == target) {
        return end
    }

    val half = (start + end) / 2
    when {
        nums[half] == target -> return half
        nums[half] > nums[start] -> // 有序
            return if (nums[start] < target && nums[half] > target) {
                val result = nums.binarySearch(target, start, half)
                if (result >= 0) result else -1
            } else {
                search(nums, target, half + 1, end)
            }
        nums[half] < nums[end] -> // 有序
            return if (nums[half] < target && nums[end] > target) {
                val result = nums.binarySearch(target, half, end)
                if (result >= 0) result else -1
            } else {
                search(nums, target, start, half - 1)
            }
        else -> // 主要是一些half和start,end相等的情况
            return -1
    }
}

fun main() {
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 0))
    println(search(intArrayOf(4, 5, 6, 7, 0, 1, 2), 3))
}

