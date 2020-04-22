package algorithm

/**
将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) {
        return l2
    }

    if (l2 == null) {
        return l1
    }

    var node1: ListNode? = l1
    var node2: ListNode? = l2

    var head: ListNode? = null
    var tail: ListNode? = null

    while (node1 != null || node2 != null) {
        var min: ListNode? = null

        if (node1 != null && (node2 == null || node1.`val` <= node2.`val`)) {
            min = node1
            node1 = node1.next
        } else {
            if (node2 != null && (node1 == null || node2.`val` < node1.`val`)) {
                min = node2
                node2 = node2.next
            }
        }

        if (tail == null) {
            tail = min
            head = tail
        } else {
            tail.next = min
            tail = min
        }
    }
    return head
}

