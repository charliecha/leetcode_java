package algorithm

/**
 * 给定一个单词列表，我们将这个列表编码成一个索引字符串 S 与一个索引列表 A。

例如，如果这个列表是 ["time", "me", "bell"]，我们就可以将其表示为 S = "time#bell#" 和 indexes = [0, 2, 5]。

对于每一个索引，我们可以通过从字符串 S 中索引的位置开始读取字符串，直到 "#" 结束，来恢复我们之前的单词列表。

那么成功对给定单词列表进行编码的最小字符串长度是多少呢？

 

示例：

输入: words = ["time", "me", "bell"]
输出: 10
说明: S = "time#bell#" ， indexes = [0, 2, 5] 。
 

提示：

1 <= words.length <= 2000
1 <= words[i].length <= 7
每个单词都是小写字母 。

通过单词反向和二分查找，优化算法性能
 */
fun minimumLengthEncoding2(words: Array<String>): Int {
    val resultList = mutableListOf<String>()
    for (word in words) {
        val reversedWord = word.reversed()
        val i = resultList.binarySearch(reversedWord)
        if (i < 0) {
            val index = -i - 1
            if (index > 0 && reversedWord.startsWith(resultList[index - 1])) {
                resultList[index - 1] = reversedWord
                continue
            } else if (index < resultList.size && resultList[index].startsWith(reversedWord)) {
                continue
            }

            resultList.add(index, reversedWord)
        }
    }

    var count = resultList.size
    for (r in resultList) {
        count += r.length
    }
    return count
}

fun main() {
    // "time", "me", "bell"
    val words = Array(3) {
        when (it) {
            0 -> "time"
            1 -> "me"
            else -> "bell"
        }
    }
    println(minimumLengthEncoding2(words))
}