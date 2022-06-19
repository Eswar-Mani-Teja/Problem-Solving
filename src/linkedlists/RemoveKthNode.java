package linkedlists;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static linkedlists.LinkedList.*;

public class RemoveKthNode {

    public static void main(String[] args) {
        var root = getInput();
        int k = 8;
        printLinkedList("{inp}", root);

        root = removeKthNode(root, k);
        printLinkedList("{Rec}", root);

        root = getInput();
        root = removeKthNodeITR(root, k);
        printLinkedList("{ITR}", root);
    }

    private static Node<Integer> getInput() {
        return createLinkedList(
                IntStream.rangeClosed(1, 7)
                        .boxed()
                        .collect(Collectors.toList())
        );
    }

    private static Node<Integer> removeKthNode(Node<Integer> root, int k) {
        if (root == null) return root;
        if (k == 1) return root.next;
        root.next = removeKthNode(root.next, k - 1);
        return root;
    }

    private static Node<Integer> removeKthNodeITR(Node<Integer> root, int k) {
        var current = root;
        Node<Integer> prev = null;
        while (current != null && k > 1) {
            prev = current;
            current = current.next;
            k--;
        }
        if (k == 1 && current != null) {
            if (prev == null) return current.next;
            prev.next = current.next;
        }
        return root;
    }
}
