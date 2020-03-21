package algorithm

import java.util.*
import kotlin.text.StringBuilder

/**
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);
示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"
示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L     D     R
E   O E   I I
E C   I H   N
T     S     G
 */
fun convert(s: String, numRows: Int): String {
    // 转化本质上就是位置变化问题
    if (s.isEmpty() || numRows <= 1) {
        return s
    }

    // 元素的索引数组
    val indexArray = MutableList(s.length) {
        it
    }

    // 元素被摆放的位置数组
    val rowLocation = mutableListOf<Int>()
    val colLocation = mutableListOf<Int>()

    // 摆放元素的位置
    arrange(indexArray.size, numRows, rowLocation, colLocation)

    // 按照新的规则排序，获取新的元素的索引位置
    indexArray.sortWith(Comparator { o1, o2 -> compare(o1, o2, rowLocation, colLocation) })

    val stringBuilder = StringBuilder()
    for (i in 0 until indexArray.size) {
        stringBuilder.append(s[indexArray[i]])
    }
    return stringBuilder.toString()
}

fun arrange(size: Int, numRows: Int, rowLocation: MutableList<Int>, colLocation: MutableList<Int>) {
    var row = 0
    var col = 0
    for (i in 0 until size) {
        rowLocation.add(row)
        colLocation.add(col)
        if (col % (numRows - 1) == 0 && row != numRows - 1) {
            row++
        } else {
            col++
            row--
        }
    }
}

fun compare(o1: Int, o2: Int, rowLocation: List<Int>, colLocation: List<Int>): Int {
    return when {
        rowLocation[o1] < rowLocation[o2] -> -1
        rowLocation[o1] > rowLocation[o2] -> 1
        colLocation[o1] < colLocation[o2] -> -1
        colLocation[o1] > colLocation[o2] -> 1
        else -> 1
    }
}

fun main() {
    println(convert("LEETCODEISHIRING", 3))
    println(convert("LEETCODEISHIRING", 4))
}