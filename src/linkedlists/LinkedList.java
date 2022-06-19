package linkedlists;

import java.util.List;

public class LinkedList {
    static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

    }

    public static <T> Node<T> createLinkedList(List<T> list) {
        Node<T> root = null;
        Node<T> prev = null;
        Node<T> current;
        for (var item : list) {
            current = new Node<>(item);
            if (root == null) root = current;
            else prev.next = current;
            prev = current;
        }
        return root;
    }

    public static <T> void printLinkedList(Node<T> root) {
        printLinkedList("", root);
    }

    public static <T> void printLinkedList(String tag, Node<T> root) {
        tag = !tag.isEmpty() && !tag.strip().endsWith(":") ? tag + ": " : tag;
        System.out.print(tag + "[head]");
        while (root != null) {
            System.out.print(root.data + "-> ");
            root = root.next;
        }
        System.out.println("null");
    }
}
