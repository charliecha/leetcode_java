package algorithm

import java.util.*
import kotlin.math.abs

/**
给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。

要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点。

 

示例 1：

输入：
line1 = {0, 0}, {1, 0}
line2 = {1, 1}, {0, -1}
输出： {0.5, 0}
示例 2：

输入：
line1 = {0, 0}, {3, 3}
line2 = {1, 1}, {2, 2}
输出： {1, 1}
示例 3：

输入：
line1 = {0, 0}, {1, 1}
line2 = {1, 0}, {2, 1}
输出： {}，两条线段没有交点
 

提示：

坐标绝对值不会超过 2^7
输入的坐标均是有效的二维坐标

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/intersection-lcci
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun intersection(start1: IntArray, end1: IntArray, start2: IntArray, end2: IntArray): DoubleArray {
    var startPoint1 = Point(start1[0].toDouble(), start1[1].toDouble())
    var endPoint1 = Point(end1[0].toDouble(), end1[1].toDouble())

    var startPoint2 = Point(start2[0].toDouble(), start2[1].toDouble())
    var endPoint2 = Point(end2[0].toDouble(), end2[1].toDouble())

    while (true) {
        if (inLine(startPoint1, endPoint1, startPoint2, endPoint2)) {
            if (between(startPoint1, endPoint1, startPoint2)) {
                return doubleArrayOf(startPoint2.x, startPoint2.y)
            }

            if (between(startPoint2, endPoint2, startPoint1)) {
                return doubleArrayOf(startPoint1.x, startPoint1.y)
            }

            if (between(startPoint1, endPoint1, endPoint2)) {
                return doubleArrayOf(endPoint2.x, endPoint2.y)
            }

            if (between(startPoint2, endPoint2, endPoint1)) {
                return doubleArrayOf(endPoint1.x, endPoint1.y)
            }
            break
        }

        // 是否处于直线中
        if (inLine(startPoint1, endPoint1, startPoint2)) {
            if (between(startPoint1, endPoint1, startPoint2)) {
                return doubleArrayOf(startPoint2.x, startPoint2.y)
            }
        }

        if (inLine(startPoint2, endPoint2, startPoint1)) {
            if (between(startPoint2, endPoint2, startPoint1)) {
                return doubleArrayOf(startPoint1.x, startPoint1.y)
            }
        }

        if (inLine(startPoint1, endPoint1, endPoint2)) {
            if (between(startPoint1, endPoint1, endPoint2)) {
                return doubleArrayOf(endPoint2.x, endPoint2.y)
            }
        }

        if (inLine(startPoint2, endPoint2, endPoint1)) {
            if (between(startPoint2, endPoint2, endPoint1)) {
                return doubleArrayOf(endPoint1.x, endPoint1.y)
            }
        }

        // 不相交
        if (inOneSide(startPoint1, endPoint1, startPoint2, endPoint2)) {
            break
        }

        // 不相交
        if (inOneSide(startPoint2, endPoint2, startPoint1, endPoint1)) {
            break
        }

        val halfPorint1 = Point((startPoint1.x + endPoint1.x) / 2, (startPoint1.y + endPoint1.y) / 2)
        val halfPorint2 = Point((startPoint2.x + endPoint2.x) / 2, (startPoint2.y + endPoint2.y) / 2)

        if (equals(halfPorint1.x, halfPorint2.x) && equals(halfPorint1.y, halfPorint2.y)) {
            return doubleArrayOf(halfPorint1.x, halfPorint1.y)
        }

        if (inLine(startPoint1, endPoint1, halfPorint2)) {
            if (between(startPoint1, endPoint1, halfPorint2)) {
                return doubleArrayOf(halfPorint2.x, halfPorint2.y)
            }
        }

        if (inLine(startPoint2, endPoint2, halfPorint1)) {
            if (between(startPoint2, endPoint2, halfPorint1)) {
                return doubleArrayOf(halfPorint1.x, halfPorint1.y)
            }
        }

        if (inOneSide(startPoint2, endPoint2, startPoint1, halfPorint1)) {
            startPoint1 = halfPorint1
        } else {
            endPoint1 = halfPorint1
        }

        if (inOneSide(startPoint1, endPoint1, startPoint2, halfPorint2)) {
            startPoint2 = halfPorint2
        } else {
            endPoint2 = halfPorint2
        }
    }

    return doubleArrayOf()
}

private class Point(val x: Double, val y: Double) {
    override fun toString(): String {
        return "Point(x=$x, y=$y)"
    }
}

private fun inOneSide(linePoint1: Point, linePoint2: Point, point1: Point, point2: Point): Boolean {
    if (equals(linePoint1.x, linePoint2.x)) {
        // 垂直
        return (point1.x - linePoint1.x) * ((point2.x - linePoint1.x)) > 0
    }

    if (equals(linePoint1.y, linePoint2.y)) {
        // 垂直
        return (point1.y - linePoint1.y) * ((point2.y - linePoint1.y)) > 0
    }

    val rightAngleFocus1 = rightAngleFocus(linePoint1, linePoint2, point1)
    val rightAngleFocus2 = rightAngleFocus(linePoint1, linePoint2, point2)
    return (point2.x - rightAngleFocus2.x) * (point1.x - rightAngleFocus1.x) > 0
}

private fun inLine(point1: Point, point2: Point, point3: Point, point4: Point): Boolean {
    return inLine(point1, point2, point3) && inLine(point1, point2, point4)
}

private fun inLine(point1: Point, point2: Point, point: Point): Boolean {
    if (equals(point1.x, point2.x) && equals(point.x, point1.x)) {
        // 垂直
        return true
    }

    if (equals(point1.y, point2.y) && equals(point.y, point1.y)) {
        // 垂直
        return true
    }

    // 斜率
    val s1 = (point2.y - point1.y) / (point2.x - point1.x)
    val s2 = (point.y - point1.y) / (point.x - point1.x)

    return equals(s1, s2)
}

private fun between(point1: Point, point2: Point, point: Point) : Boolean {
    return (point.x - point1.x) * (point2.x - point.x)>= 0
            && (point.y - point1.y) * (point2.y - point.y)>= 0
}


private fun rightAngleFocus(point1: Point, point2: Point, point: Point): Point {
    if (equals(point1.x, point2.x)) {
        // 垂直
        return Point(point1.x, point.y)
    }

    if (equals(point1.y, point2.y)) {
        // 水平
        return Point(point.x, point1.y)
    }

    // 斜率
    val s = (point2.y - point1.y) / (point2.x - point1.x)
    val x = ((s * point2.x + point.x / s) - (point2.y - point.y)) / (s + 1 / s)
    val y = point2.y - s * (point2.x - x)
    return Point(x, y)
}

private fun equals(d1: Double, d2: Double) = abs(d1 - d2) < 0.000001

fun main() {
//    val point2 = Point(1.0, 1.0)
//    val point1 = Point(-1.0, -1.0)
//    val point = Point(0.5, 0.0)
//    println(rightAngleFocus(point1, point2, point))

//    println(Arrays.toString(intersection(intArrayOf(0, 0), intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(0, -1))))
//    println(Arrays.toString(intersection(intArrayOf(0, 0), intArrayOf(3, 3), intArrayOf(1, 1), intArrayOf(2, 2))))
//    println(Arrays.toString(intersection(intArrayOf(0, 0), intArrayOf(1, 1), intArrayOf(1, 0), intArrayOf(2, 1))))
    println(Arrays.toString(intersection(intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3))))
}