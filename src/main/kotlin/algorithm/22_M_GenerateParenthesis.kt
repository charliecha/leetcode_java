package algorithm

import java.util.*

/**
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

示例：

输入：n = 3
输出：[
"((()))",
"(()())",
"(())()",
"()(())",
"()()()"
]

()
(() ()(
(())  ()()
((())) (()()) (())() (()()) ()(()) ()()()

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/generate-parentheses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

利用递归思想，找到规律

求解问题没有思路时，一种通过归纳思想，从特列找出普遍规律；
一种是演绎的思想，是否能够把问题进行分解。
 */
fun generateParenthesis(n: Int): List<String> {
    var parenthesis = mutableSetOf("()")

    for (i in 2 until n + 1) {
        val lastParenthesis = parenthesis
        parenthesis = mutableSetOf()
        for (p in lastParenthesis) {
            var lastIndex = 0
            for (j in 0 until p.length) {
                if (p[j] == ')') {
                    addParenthesis(parenthesis, p, j)
                    lastIndex = j
                }
            }
            addParenthesis(parenthesis, p, lastIndex + 1)
        }
    }

//    println(parenthesis.size)
//    println(Arrays.toString(parenthesis.toTypedArray()))
    return parenthesis.asSequence().toList()
}

private fun addParenthesis(
    parenthesis: MutableSet<String>,
    p: String,
    i: Int
) {
    val p2 = if (i == p.length) {
        "$p()"
    } else {
        p.substring(0, i) + "(" + p.substring(i) + ")"
    }

    if (!parenthesis.contains(p2)) {
        parenthesis.add(p2)
    }
}

fun main() {
    generateParenthesis(3)
}
