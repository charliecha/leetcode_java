package algorithm

import java.lang.StringBuilder

fun lengthOfLongestSubstring(s: String): Int {
    if (s.isEmpty()) {
        return 0
    }

    if (s.length == 1) {
        return 1
    }

    val stringBuilder = StringBuilder()
    var max = 0
    for (i in 0 until s.length - 1) {
        stringBuilder.clear()
        stringBuilder.append(s[i])

        for (j in i + 1 until s.length) {
            if (stringBuilder.contains(s[j])) {
                break
            }

            stringBuilder.append(s[j])
        }
        if (stringBuilder.length > max) {
            max = stringBuilder.length
        }
    }
    return max
}

fun main() {
    println(lengthOfLongestSubstring("abcabcbb"))
    println(lengthOfLongestSubstring("aa"))
    println(lengthOfLongestSubstring("pwwkew"))
}