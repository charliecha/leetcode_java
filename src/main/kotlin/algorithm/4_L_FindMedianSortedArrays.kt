package algorithm

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
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
    if (nums1.isEmpty()) {
        return findMedianSortedArrays(nums2)
    }

    if (nums2.isEmpty()) {
        return findMedianSortedArrays(nums1)
    }

    val len = (nums1.size + nums2.size - 1) / 2
    var start1 = 0
    var start2 = 0
    var end1 = nums1.size - 1
    var end2 = nums2.size - 1

    for (i in 0 until len) {
        when {
            start2 >= nums2.size -> {
                start1++
            }
            start1  >= nums1.size -> {
                start2++
            }
            nums1[start1] < nums2[start2] -> {
                start1++
            }
            else -> {
                start2++
            }
        }

        when {
            end2 < 0 -> {
                end1--
            }
            end1 < 0 -> {
                end2--
            }
            nums1[end1] > nums2[end2] -> {
                end1--
            }
            else -> {
                end2--
            }
        }
    }

    println("$start1 ： $end1 | $start2 ： $end2 ")

    // 第一个数组返回2个中位数
    if (start1 < end1) {
        return (nums1[start1] + nums1[end1]).toDouble() / 2
    }

    // 第二个数组返回2个中位数
    if (start2 < end2) {
        return (nums2[start2] + nums2[end2]).toDouble() / 2
    }

    // 第二个数组返回1个中位数
    if (start1 > end1) {
        return nums2[start2].toDouble()
    }

    // 第一个数组返回1个中位数
    if (start2 > end2) {
        return nums1[start1].toDouble()
    }

    // 第一个数组，第二个数组各返回1个中位数
    return (nums1[start1] + nums2[start2]).toDouble() / 2
}

fun findMedianSortedArrays(nums: IntArray): Double {
    if (nums.isEmpty()) {
        throw IllegalArgumentException("empty list")
    }


    val half = nums.size / 2

    // 奇数个
    if ((nums.size + 1) % 2 == 0) {
        return nums[half].toDouble()
    }

    // 偶数个
    return (nums[half] + nums[half - 1]) / 2.toDouble()
}


fun main() {
    val nums1 = intArrayOf(1)
    val nums2 = intArrayOf(2, 3, 4, 5)

    println(findMedianSortedArrays(nums1, nums2))
}