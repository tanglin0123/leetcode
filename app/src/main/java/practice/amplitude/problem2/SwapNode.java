package practice.amplitude.problem2;

public class SwapNode {
    public static class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public Node swapNode(Node head, int n) {
        if (head == null) {
            throw new RuntimeException("Empty head");
        }

        if (n <= 0) {
            throw new RuntimeException("Invalid N: " + n);
        }

        Node slow = head;
        Node fast = head;

        int i = 0;
        while (i < n && fast != null) {
            fast = fast.next;
            ++i;
        }

        if(fast == null) {
            throw new RuntimeException("Not long enough list, length = " + i);
        } 

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        Node lastN = slow.next;
        Node second = head.next;
        slow.next = head;
        head.next = lastN.next;
        lastN.next = second;

        return lastN;
    }


    static Node prepareList1() {
        Node node6 = new Node(6, null);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        
        return node1;
    }

    static Node prepareList2() {

        Node node3 = new Node(3, null);
        Node node2 = new Node(2, node3);
        Node node1 = new Node(1, node2);
        
        return node1;
    }


    static String listToString(Node head) {
        String result = "";

        Node current = head;

        while (current != null) {
            result += current.value + " -> ";
            current = current.next;
        }

        return result;
    }

    public static void main(String[] args) {
        SwapNode swapNode = new SwapNode();

        Node head1 = prepareList1();
        
        Node newHead1 = swapNode.swapNode(head1, 2);

        System.out.println(listToString(newHead1));

        Node head2 = prepareList2();
        
        Node newHead2 = swapNode.swapNode(head2, 4);

        System.out.println(listToString(newHead2));
    }
}
