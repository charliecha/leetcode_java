package algorithm

import java.lang.IllegalArgumentException

/**
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
 */
fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
    if (nums1.isEmpty() && nums2.isEmpty()) {
        throw IllegalArgumentException("both empty array")
    }

    if (nums1.size < nums2.size) {
        return findMedianSortedArrays(
            nums1, 0, nums1.size - 1,
            nums2, 0, nums2.size - 1
        )
    } else {
        return findMedianSortedArrays(
            nums2, 0, nums2.size - 1,
            nums1, 0, nums1.size - 1
        )
    }
}

fun findMedianSortedArrays(
    min: IntArray, minStart: Int, minEnd: Int,
    max: IntArray, maxStart: Int, maxEnd: Int
): Double {
    // 都只剩一个元素
    if (minStart == minEnd && maxStart == maxEnd) {
        return (min[minStart] + max[maxStart]).toDouble() / 2
    }

    // min被消解掉了
    if (minStart > maxEnd) {
        val len = maxEnd - maxStart + 1;
        val half = maxStart + len / 2
        if (len % 2 == 1) {
            // 奇数
            return max[half].toDouble()
        } else {
            return (max[half] + max[half - 1]).toDouble() / 2
        }
    }

    // 分治
    val minHalf = (minStart + minEnd) / 2
    val maxHalf = (maxStart + maxEnd) / 2

    if (min[minHalf] == max[maxHalf]) {
        // 都为偶数
        if (min.size % 2 == 0 && max.size % 2 == 0) {
            return (min[minHalf] + Math.min(min[minHalf + 1], max[maxHalf + 1])).toDouble() / 2
        } else {
            return min[minHalf].toDouble()
        }
    } else if (min[minHalf] > max[maxHalf]) {
        val step = minEnd - minHalf
        if (step == 0) {
            return findMedianSortedArrays(min, minStart, minHalf, max, maxStart + step, maxEnd)
        } else {
            return findMedianSortedArrays(min, minStart, minHalf, max, maxStart + step, maxEnd)
        }
    } else {
        val step = minHalf - minStart
        if (step == 0) {
            return findMedianSortedArrays(min, minStart + 1, minEnd, max, maxStart + 1, maxEnd)
        } else {
            return findMedianSortedArrays(min, minHalf, minEnd, max, maxStart + step, maxEnd)
        }
    }
}


fun main() {
    val nums1 = intArrayOf(1, 11, 45, 55, 66, 99)
    val nums2 = intArrayOf(2, 11, 51, 122, 99)

    println(findMedianSortedArrays2(nums1, nums2))
}