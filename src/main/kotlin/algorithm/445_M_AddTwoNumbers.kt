package algorithm

import java.util.*

/**
给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

 

进阶：

如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

 

示例：

输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 8 -> 0 -> 7

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/add-two-numbers-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun addTwoNumbers3(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
        return l2
    }

    if (l2 == null) {
        return l1
    }

    var n: ListNode?

    val stack1 = Stack<Int>()
    n = l1
    while (n != null) {
        stack1.push(n.`val`)
        n = n.next
    }

    n = l2
    val stack2 = Stack<Int>()
    while (n != null) {
        stack2.push(n.`val`)
        n = n.next
    }

    var root = ListNode(0)
    var remaining = 0
    while (stack1.isNotEmpty() || stack2.isNotEmpty() || remaining != 0) {
        remaining += if (stack1.isEmpty()) {
            0
        } else {
            stack1.pop()
        }

        remaining+= if (stack2.isEmpty()) {
            0
        } else {
            stack2.pop()
        }

        root.`val` = remaining % 10
        remaining /= 10

        if (stack1.isNotEmpty() || stack2.isNotEmpty() || remaining != 0) {
            val t = ListNode(0)
            t.next = root
            root = t
        }
    }

    return root
}

fun main() {
    val l1 = ListNode(2)
    l1.next = ListNode(4)
    l1.next?.next = ListNode(3)

    val l2 = ListNode(5)
    l2.next = ListNode(6)
    l2.next?.next = ListNode(4)

    println(l1)

    println(l2)

    println(addTwoNumbers3(l1, l2))
}