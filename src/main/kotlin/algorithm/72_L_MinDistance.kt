package algorithm

/**
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符
 

示例 1：

输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
示例 2：

输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')

// 动态规划问题，通过把问题分解

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/edit-distance
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun minDistance(word1: String, word2: String): Int {
    val row = word1.length + 1
    val col = word2.length + 1

    val distance = Array(row) {
        Array(col) {
            0
        }
    }

    for (i in 1 until col) {
        distance[0][i] = i
    }

    for (i in 1 until row) {
        distance[i][0] = i
    }

    for (i in 1 until row) {
        for (j in 1 until col) {
            if (word1[i - 1] == word2[j - 1]) {
                distance[i][j] = distance[i - 1][j - 1]
            } else {
                distance[i][j] = kotlin.math.min(
                    distance[i - 1][j - 1],
                    kotlin.math.min(distance[i - 1][j], distance[i][j - 1])
                ) + 1
            }
        }
    }

    return distance[row - 1][col - 1]
}

fun main() {
    println(minDistance("horse", "ros"))
    println(minDistance("intention", "execution"))
}