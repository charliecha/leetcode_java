package algorithm

/**
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/longest-common-prefix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun longestCommonPrefix(strs: Array<String>): String {
    if (strs.isEmpty()) {
        return ""
    }

    val s = strs[0]
    var end = 0
    var exit = false

    for (i in 0 until s.length) {
        for (j in 1 until strs.size) {
            if (i >= strs[j].length || strs[j][i] != s[i]) {
                exit = true
                break
            }
        }

        if (exit) {
            break
        }
        end++
    }

    return s.substring(0, end)
}

fun main() {
    val strs = Array(3) {
        when (it) {
            0 -> "flower"
            1 -> "flow"
            else -> "flight"
        }
    }
    println(longestCommonPrefix(strs) == "fl")

    val strs2 = Array(3) {
        when (it) {
            0 -> "dog"
            1 -> "racecar"
            else -> "car"
        }
    }
    println(longestCommonPrefix(strs2) == "")
}