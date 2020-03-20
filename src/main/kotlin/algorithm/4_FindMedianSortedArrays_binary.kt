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

    return if (nums1.size < nums2.size) {
        findMedianSortedArrays(
            nums1, 0, nums1.size - 1,
            nums2, 0, nums2.size - 1
        )
    } else {
        findMedianSortedArrays(
            nums2, 0, nums2.size - 1,
            nums1, 0, nums1.size - 1
        )
    }
}

fun findMedianSortedArrays(
    min: IntArray, minStart: Int, minEnd: Int,
    max: IntArray, maxStart: Int, maxEnd: Int
): Double {
    // min只剩一个元素
    if (minStart == minEnd) {
        // max只有一个元素
        if (maxStart == maxEnd) {
            return (min[minStart] + max[maxStart]).toDouble() / 2
        }

        val maxHalf = (maxStart + maxEnd) / 2
        if ((maxEnd - maxStart) % 2 != 0) {
            // 偶数
            return when {
                min[minStart] < max[maxHalf] -> {
                    max[maxHalf].toDouble()
                }
                min[minStart] > max[maxHalf + 1] -> {
                    max[maxHalf + 1].toDouble()
                }
                else -> {
                    min[minStart].toDouble()
                }
            }
        } else {
            // 奇数
            return when {
                min[minStart] < max[maxHalf - 1] -> {
                    (max[maxHalf - 1] + max[maxHalf]) / 2.toDouble()
                }
                min[minStart] < max[maxHalf + 1] -> {
                    (min[minStart] + max[maxHalf]) / 2.toDouble()
                }
                else -> {
                    (max[maxHalf] + max[maxHalf + 1]) / 2.toDouble()
                }
            }
        }
    }

    // min被消解掉了
    if (minStart > minEnd) {
        val len = maxEnd - maxStart + 1
        val half = maxStart + len / 2
        return if (len % 2 == 1) {
            // 奇数
            max[half].toDouble()
        } else {
            (max[half] + max[half - 1]).toDouble() / 2
        }
    }

    val minHalf = (minStart + minEnd) / 2
    val maxHalf = (maxStart + maxEnd) / 2
    // 分治,递归
    when {
        min[minHalf] == max[maxHalf] -> {
            // 都为偶数
            return if (min.size % 2 == 0 && max.size % 2 == 0) {
                (min[minHalf] + kotlin.math.min(min[minHalf + 1], max[maxHalf + 1])).toDouble() / 2
            } else {
                min[minHalf].toDouble()
            }
        }
        min[minHalf] > max[maxHalf] -> {
            val step = minEnd - minHalf
            return if (step == 0) {
                if (min[minStart] < max[maxStart]) {
                    findMedianSortedArrays(min, minStart + 1, minEnd - 1, max, maxStart, maxEnd)
                } else {
                    findMedianSortedArrays(min, minStart, minEnd - 1, max, maxStart + 1, maxEnd)
                }
            } else {
                findMedianSortedArrays(min, minStart, minHalf, max, maxStart + step, maxEnd)
            }
        }
        else -> {
            val step = minHalf - minStart
            return if (step == 0) {
                if (min[minEnd] > max[maxEnd]) {
                    findMedianSortedArrays(min, minStart + 1, minEnd - 1, max, maxStart, maxEnd)
                } else {
                    findMedianSortedArrays(min, minStart + 1, minEnd, max, maxStart, maxEnd - 1)
                }
            } else {
                findMedianSortedArrays(min, minHalf, minEnd, max, maxStart, maxEnd - step)
            }
        }
    }
}


fun main() {
    val nums1 = intArrayOf(1, 2)
    val nums2 = intArrayOf(-1, 3)

    println(findMedianSortedArrays2(nums1, nums2))
}