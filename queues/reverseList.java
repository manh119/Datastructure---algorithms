class reverseList {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode curNode = head;
        ListNode nextNode = head.next;
        ListNode newHead = null;
        while (nextNode != null) {
            curNode.next = newHead;
            newHead = curNode;
            curNode = nextNode;
            nextNode = nextNode.next;
            if (nextNode == null) {
                curNode.next = newHead;
                newHead = curNode;
            }
        }
        return newHead;

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        ListNode head2 = head;
        while (head2 != null) {
            System.out.println(head2.val);
            head2 = head2.next;
        }
        ListNode newHead = new reverseList().reverseList(head);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }

        // case head have 1 element
        ListNode head3 = new ListNode(1);
        newHead = new reverseList().reverseList(head3);
        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }
}