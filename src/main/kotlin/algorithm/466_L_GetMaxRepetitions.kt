package algorithm

fun getMaxRepetitions(s1: String, n1: Int, s2: String, n2: Int): Int {
    val repeatCnt = Array<Int>(n1 + 1) {0}
    val nextIndex = Array<Int>(n1 + 1) {0}

    var cnt = 0
    var index = 0
    for(i in 1 until n1 + 1) {
        for (j in 0 until s1.length) {
            if (s1[j] == s2[index]) {
                if (index == s2.length - 1) {
                    cnt++
                    index = 0
                } else {
                    index++
                }
            }
        }

        repeatCnt[i] = cnt
        nextIndex[i] = index

        for (k in 1 until i) {
            // 重复模式出现
            if (nextIndex[k] == nextIndex[i]) {
                val interval = i - k
                val r = (n1 - k + 1) / interval
                val repeats = r * (repeatCnt[i] - repeatCnt[k])
                val remains = repeatCnt[n1 - r * interval]
                return (repeats + remains) / n2
            }
        }
    }

//    println(Arrays.toString(repeatCnt))
//    println(Arrays.toString(nextIndex))
    return cnt / n2
}

fun main() {
//    "bacaba"
//    3
//    "abacab"
//    1

    println(getMaxRepetitions("aaa", 3, "aa", 2))
//    println(getMaxRepetitions("bacaba", 3, "abacab", 2))
}