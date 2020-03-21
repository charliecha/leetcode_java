package algorithm

/**
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
示例 2：

输入: "cbbd"
输出: "bb"
 */
fun longestPalindrome(s: String): String {
    if (s.isEmpty() || s.length == 1) {
        return s
    }

    var result = s.substring(0, 1)

    // 问题可以转化为寻找最大长度的二叉对称树
    val len = s.length
    // 以数组元素为根节点
    for (i in 1 until len - 1) {
        var step = 0
        while (i - step - 1 >= 0 && i + step + 1 < len
            && s[i - step - 1] == s[i + step + 1]
        ) {
            step++
        }
        if (2 * step + 1 > result.length) {
            result = s.substring(i - step, i + step + 1)
        }
    }

    // 以数组元素中间空白为根节点
    for (i in 1 until len) {
        var step = 0
        while (i - step - 1 >= 0 && i + step < len
            && s[i - step - 1] == s[i + step]
        ) {
            step++
        }
        if (2 * step > result.length) {
            result = s.substring(i - step, i + step)
        }
    }
    return result
}

fun main() {
    println(longestPalindrome("bb"))
//    println(longestPalindrome("babad"))
}