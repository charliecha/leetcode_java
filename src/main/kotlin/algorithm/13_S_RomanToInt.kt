package algorithm

fun romanToInt(s: String): Int {
    val size = 13
    val units = Array(size) {
        when (it) {
            0 -> 1000
            1 -> 900
            2 -> 500
            3 -> 400
            4 -> 100
            5 -> 90
            6 -> 50
            7 -> 40
            8 -> 10
            9 -> 9
            10 -> 5
            11 -> 4
            else -> 1
        }
    }
    val labels = Array(size) {
        when (it) {
            0 -> "M"
            1 -> "CM"
            2 -> "D"
            3 -> "CD"
            4 -> "C"
            5 -> "XC"
            6 -> "L"
            7 -> "XL"
            8 -> "X"
            9 -> "IX"
            10 -> "V"
            11 -> "IV"
            else -> "I"
        }
    }

    var count = 0
    var index = 0
    for (i in 0 until labels.size) {
        while (s.startsWith(labels[i], index)) {
            count += units[i]
            index += labels[i].length
        }
    }

    return count
}

fun main() {
    println(3 == romanToInt("III"))
    println(4 == romanToInt("IV"))
    println(9 == romanToInt("IX"))
    println(58 == romanToInt("LVIII"))
    println(1994 == romanToInt("MCMXCIV"))
}