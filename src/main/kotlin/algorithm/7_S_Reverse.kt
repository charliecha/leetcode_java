package algorithm

import kotlin.math.abs

/**
 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
 */
fun reverse(x: Int): Int {
    if (x == Int.MIN_VALUE) {
        return 0
    }

    var input = abs(x)

    val digits = mutableListOf<Int>()
    while (input != 0) {
        digits.add(input % 10)
        input /= 10
    }

    var result : Long = 0
    for (digit in digits) {
        result = result * 10 + digit
    }

    if (result > Int.MAX_VALUE) {
        return 0
    }
    return result.toInt()
}


fun main() {
    println(reverse(123))
    println(reverse(-123))
    println(reverse(120))
}