package algorithm

/**
 *
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
        if (start2 >= nums2.size || nums1[start1] < nums2[start2]) {
            start1++
        } else {
            start2++
        }

        if (end2 < 0 || nums1[end1] > nums2[end2]) {
            end1--
        } else {
            end2--
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
    val nums1 = intArrayOf(1, 5, 57)
    val nums2 = intArrayOf(3, 4)

    println(findMedianSortedArrays(nums1, nums2))
}