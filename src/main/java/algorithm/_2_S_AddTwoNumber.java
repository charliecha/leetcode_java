package algorithm;

public class _2_S_AddTwoNumber {
    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }


        ListNode result = new ListNode(0);

        int carry = 0;
        ListNode currentResultNode = result;

        ListNode l1CurrentNode = l1;
        ListNode l2CurrentNode = l2;


        do {
            int v = (l1CurrentNode != null ? l1CurrentNode.val : 0)
                    + (l2CurrentNode != null ? l2CurrentNode.val : 0)
                    + carry;
            carry = v / 10;
            v %= 10;
            currentResultNode.val = v;

            l1CurrentNode = l1CurrentNode != null ? l1CurrentNode.next : null;
            l2CurrentNode = l2CurrentNode != null ? l2CurrentNode.next : null;

            if (l1CurrentNode != null || l2CurrentNode != null || carry != 0) {
                currentResultNode.next = new ListNode(0);
                currentResultNode = currentResultNode.next;
            }
        } while (l1CurrentNode != null || l2CurrentNode != null || carry != 0);

        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);

        System.out.println(l1);

        System.out.println(l2);

        System.out.println(addTwoNumbers(l1, l2));
    }
}
