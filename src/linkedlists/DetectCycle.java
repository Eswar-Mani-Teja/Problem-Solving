package linkedlists;

import java.util.stream.IntStream;

import static linkedlists.LinkedList.*;

public class DetectCycle {
    public static void main(String[] args) {
        var root = createLinkedList(
                IntStream.rangeClosed(1, 8).boxed().toList()
        );
        int p = 1;
        printLinkedList(root);
        makeACycleTo(root, p);
        Node<Integer> meetingPoint = detectCycle(root);
        System.out.println("Has Cycle: " + (meetingPoint != null));

        Node<Integer> loopPoint = getCyclePoint(root, meetingPoint);
        if (loopPoint != null)
            System.out.println("Cycle Point: " + loopPoint.data);
        else
            System.out.println("No loop point");
    }

    private static Node<Integer> getCyclePoint(Node<Integer> root, Node<Integer> meetingPoint) {
        if (root == null || meetingPoint == null) return null;
        while (root != meetingPoint) {
            root = root.next;
            meetingPoint = meetingPoint.next;
        }
        return root;
    }


    // returns meeting point of slow and fast
    private static Node<Integer> detectCycle(Node<Integer> root) {
        Node<Integer> slow, fast;
        slow = fast = root;
        do {
            slow = slow.next;
            if (fast.next == null) return null;
            fast = fast.next.next;
        } while (fast != null && slow != fast);
        return fast;
    }

    private static void makeACycleTo(Node<Integer> root, int p) {
        Node<Integer> link = null;
        while (root.next != null) {
            if (root.data == p) {
                link = root;
            }
            root = root.next;
        }
        if (link == null && root.data == p) link = root;
        root.next = link;
    }
}
