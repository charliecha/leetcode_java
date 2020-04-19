package algorithm

fun getMaxRepetitions(s1: String, n1: Int, s2: String, n2: Int): Int {
    var repeatCnt = 0
    var index2 = 0
    for(i in 0 until n1) {
        for (j in 0 until s1.length) {
            if (s1[j] == s2[index2]) {
                if (index2 == s2.length - 1) {
                    repeatCnt++
                    index2 = 0
                } else {
                    index2++
                }
            }
        }
    }
    return repeatCnt / n2
}

fun main() {
    getMaxRepetitions("aaa", 3, "aa", 1)
}