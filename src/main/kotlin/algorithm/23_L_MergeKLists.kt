package algorithm

import java.util.*

/**
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6

通过反序列表和栈完成合并链表的操作。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun mergeKLists(arrays: Array<ListNode?>): ListNode? {
    // 反序的头
    var reversedHead: ListNode? = null

    val lists = mutableListOf<ListNode?>().apply {
        addAll(arrays)
    }

    var hasNode: Boolean
    do {
        hasNode = false
        for (i in lists.size - 1 downTo 0) {
            val listNode = lists[i]
            if (listNode != null) {
                hasNode = true
                lists[i] = listNode.next

                if (reversedHead == null) {
                    reversedHead = ListNode(listNode.`val`)
                } else {
                    var current = reversedHead
                    var prev: ListNode? = null
                    while (current != null && listNode.`val` < current.`val`) {
                        prev = current
                        current = current.next
                    }

                    if (prev == null) {
                        listNode.next = reversedHead
                        reversedHead = listNode
                    } else {
                        listNode.next = prev.next
                        prev.next = listNode
                    }
                }
            } else {
                lists.removeAt(i)
            }
        }

    } while (hasNode)

    val stack = Stack<Int>()
    var node = reversedHead
    while (node != null) {
        stack.push(node.`val`)
        node = node.next
    }

    var head: ListNode? = null
    var tail: ListNode? = null
    while (stack.isNotEmpty()) {
        val n = stack.pop()
        if (tail == null) {
            tail = ListNode(n)
            head = tail
        } else {
            tail.next = ListNode(n)
            tail = tail.next
        }
    }
    return head
}

fun main() {
    val node1 = ListNode(1).apply {
        next = ListNode(4).apply {
            next = ListNode(5)
        }
    }

    val node2 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(4)
        }
    }

    val node3 = ListNode(2).apply {
        next = ListNode(6)
    }

    println(mergeKLists(listOf(node1, node2, node3).toTypedArray()))
}