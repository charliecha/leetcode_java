package algorithm

/**
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807

 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
        return l2
    }

    if (l2 == null) {
        return l1
    }

    var carry: Int
    var v = l1.value + l2.value
    carry = v / 10
    v %= 10
    val result = ListNode(v)

    var l1CurrentNode: ListNode? = l1.next
    var l2CurrentNode: ListNode? = l2.next
    var currentResultNode: ListNode? = result


    while (l1CurrentNode != null || l2CurrentNode != null || carry != 0) {
        v = (l1CurrentNode?.value ?: 0) + (l2CurrentNode?.value ?: 0) + carry
        carry = v / 10
        v %= 10

        currentResultNode?.next = ListNode(v)
        currentResultNode = currentResultNode?.next

        l1CurrentNode = l1CurrentNode?.next
        l2CurrentNode = l2CurrentNode?.next
    }

    return result
}

class ListNode(val value: Int) {
    var next: ListNode? = null

    override fun toString(): String {
        if (next == null) {
            return "$value"
        }
        return "$value -> $next"
    }

    fun getValue2() :Int {
        return value
    }
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

    println(addTwoNumbers(l1, l2))
}