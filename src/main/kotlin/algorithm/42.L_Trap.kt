package algorithm

/**

给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。



上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 感谢 Marcos 贡献此图。

示例:

输入: [0,1,0,2,1,0,1,3,2,1,2,1]
输出: 6
 */
fun trap2(height: IntArray): Int {
    if (height.isEmpty()) {
        return 0
    }

    var start = 0
    var count = 0

    while (start < height.size - 1) {
        while (start + 1 < height.size && height[start + 1] > height[start]) {
            start++
        }

        var end = start + 1

        if (end >= height.size) {
            break
        }

        for (i in end + 1 until height.size) {
            if (height[i] >= height[end]) {
                end = i
            }

            if (height[i] > height[start]) {
                end = i
                break
            }
        }

        println("$start -> $end")
        if (end > start) {
            count += kotlin.math.min(height[start], height[end]) * (end - start - 1)
            for (i in start + 1 until end) {
                count -= height[i]
            }
        }
        start = end
    }
    return count
}

fun main() {
    println(trap2(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1)))
}