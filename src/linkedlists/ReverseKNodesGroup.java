package linkedlists;

import java.util.List;
import java.util.stream.IntStream;

import static linkedlists.LinkedList.*;

public class ReverseKNodesGroup {
    public static void main(String[] args) {
        var input = IntStream.rangeClosed(1,8).boxed().toList();
        var root = createLinkedList(input);
        printLinkedList(root);
        int k = 7;
        root = reverseEveryKGroup(root, k, 0);
        printLinkedList(root);
    }

    private static Node<Integer> reverseNodes(Node<Integer> root, int k) {
        if (root == null) return root;
        if (k == 1) return root;
        var newHead = reverseNodes(root.next, k - 1);
        if (newHead != null) {
            var next = root.next;
            next.next = root;
            root.next = null;
        }
        return newHead;
    }

    private static Node<Integer> reverseEveryKGroup(Node<Integer> root, int k, int currentPosition) {
        if (root == null || k <= 1) return root;
        var nextHead = reverseEveryKGroup(root.next, k, currentPosition + 1);
        if (currentPosition % k == 0) {
            Node<Integer> revHead = reverseNodes(root, k);
            if (revHead != null) {
                root.next = nextHead; // As root will now become the last node
            } else {
                revHead = root; // revHead will be null if the list isn't reversed. so, reset to root
            }
            nextHead = revHead; // As next Head will be the newly reversed Head
        }
        return nextHead;
    }
}
