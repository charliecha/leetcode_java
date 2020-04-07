package algorithm

/**
给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。

不占用额外内存空间能否做到？

 

示例 1:

给定 matrix =
[
[1,2,3],
[4,5,6],
[7,8,9]
],

原地旋转输入矩阵，使其变为:
[
[7,4,1],
[8,5,2],
[9,6,3]
]
示例 2:

给定 matrix =
[
[ 5, 1, 9,11],
[ 2, 4, 8,10],
[13, 3, 6, 7],
[15,14,12,16]
],

原地旋转输入矩阵，使其变为:
[
[15,13, 2, 5],
[14, 3, 4, 1],
[12, 6, 8, 9],
[16, 7,10,11]
]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/rotate-matrix-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

 坐标转化，从外圈网内圈不断运算。
 */
fun rotate(matrix: Array<IntArray>): Unit {
    val row = matrix.size
    val col = matrix[0].size

   // printMatrix(matrix)

    for (i in 0 until row / 2) {
        for (j in i until col - 1 - i) {
            val t = matrix[i][j]  //i=0,y=1 0,1
            matrix[i][j] = matrix[row - 1 - j][i]  //1,0
            matrix[row - 1 - j][i] = matrix[row - 1 - i][col - 1 - j]  //2,1
            matrix[row - 1 - i][col - 1 - j] = matrix[j][col - 1 - i]  //1,2
            matrix[j][col - 1 - i] = t

           // printMatrix(matrix)
        }
    }
}

fun printMatrix(matrix: Array<IntArray>) {
    for (i in 0 until matrix.size) {
        for (j in 0 until matrix[i].size) {
            print(" ${matrix[i][j]} ")
        }
        println()
    }
}

fun main() {
    val matrix = Array(3) { row ->
        IntArray(3) { col ->
            3 * row + col + 1
        }
    }
    rotate(matrix)
}

