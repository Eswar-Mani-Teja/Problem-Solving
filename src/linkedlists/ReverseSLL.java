package linkedlists;

import java.util.List;

import static linkedlists.LinkedList.*;

public class ReverseSLL {

    public static void main(String[] args) {
        LinkedList.Node<Integer> root = createLinkedList(List.of(1, 2, 3, 4, 5, 6));
        printLinkedList(root);
        root = reverseLinkedList(root);
        printLinkedList("Reversed-REC", root);

        root = createLinkedList(List.of(1, 2, 3, 4, 5, 6));
        root = reverseLinkedListITR(root);
        printLinkedList("Reversed-ITR", root);
    }

    private static Node<Integer> reverseLinkedList(Node<Integer> current) {
        if (current == null) return null;
        if (current.next == null) return current;
        var root = reverseLinkedList(current.next);
        var next = current.next;
        next.next = current;
        current.next = null;
        return root;
    }

    private static Node<Integer> reverseLinkedListITR(Node<Integer> root) {
        if (root == null) return root;
        Node<Integer> prev = null;
        var current = root;
        var next = current.next;
        while (next != null) {
            current.next = prev;
            prev = current;
            current = next;
            next = current.next;
        }
        current.next = prev;
        return current;
    }
}
