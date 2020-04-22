package algorithm

/**
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？

双链表问题

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    if (head == null) {
        return head
    }

    var node  : ListNode = head
    var nNode : ListNode = head

    var c = n
    while (c > 0) {
        if (nNode.next == null) {
            break
        }

        nNode = nNode.next
        c--
    }

    if (c == 1) {
        return head.next
    }

    if (c == 0) {
        while (nNode.next != null) {
            nNode = nNode.next
            node = node.next
        }

        node.next = node.next.next
    }
    return head
}