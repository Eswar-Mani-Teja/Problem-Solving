package linkedlists;

import java.util.Scanner;

public class SingleLinkedList {

    private static class Node {
        private final int data;
        private Node nextNode;

        private Node(int data) {
            this.data = data;
        }
    }

    private Node makeALinkedList(int[] arr, int N) {
        Node root = null;
        Node prevNode = null;
        Node currentNode;
        for (int i = 0; i < N; i++) {
            currentNode = new Node(arr[i]);
            if (root == null) {
                root = currentNode;
            } else {
                prevNode.nextNode = currentNode;
            }
            prevNode = currentNode;
        }
        return root;
    }

    private static void printLinkedList(Node root) {
        Node traverse = root;
        while (traverse != null) {
            System.out.print(traverse.data + " -> ");
            traverse = traverse.nextNode;
        }
        System.out.println("null");
    }

    private Node getLinkedListFromInput(Scanner scanner) {
        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        return makeALinkedList(arr, N);
    }

    private Node reverseLinkedListRecursively(Node currentNode) {
        if (currentNode == null || currentNode.nextNode == null) {
            return currentNode;
        }
        Node root = reverseLinkedListRecursively(currentNode.nextNode);
        Node nextNode = currentNode.nextNode;
        nextNode.nextNode =  currentNode;
        currentNode.nextNode = null;

        return root;
    }

    private Node reverseLinkedListIteratively(Node root) {
        if (root == null) return root;
        Node current = root;
        Node next;
        Node prev = null;

        while (current != null) {
            next = current.nextNode;
            current.nextNode = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    private Node removeKthNodeIteratively(Node root, int k) {
        Node current = root;
        Node prevNode = null;
        while (current != null && k > 1) {
            prevNode = current;
            current = current.nextNode;
            k--;
        }
        if (current != null && k == 1) {
            if (prevNode != null)
                prevNode.nextNode = current.nextNode;
            else
                current = current.nextNode;
        } else {
            current = root;
        }
        return current;
    }

    private Node removeKthNodeRecursively(Node root, int k) {
        if (root == null) {
            return root;
        }
        if (k == 1) {
            return root.nextNode;
        }
        root.nextNode = removeKthNodeRecursively(root.nextNode, k - 1);
        return root;
    }

    private Node removeOddNodesRecursively(Node current, boolean odd) {
        if (current == null) {
            return current;
        }
        Node oddNode = removeOddNodesRecursively(current.nextNode, !odd);
        if (!odd) {
            current.nextNode = oddNode;
            return current;
        } else {
            return oddNode;
        }
    }

    private Node removeOddNodesIteratively(Node root, boolean odd) {
//        Node current = root;
//        Node nextNode = root.nextNode;
//        while (nextNode != null) {
//            if (odd) {
//                current.nextNode = nextNode.nextNode;
//                current = nextNode.nextNode;
//            }
//            odd = !odd;
//            nextNode = nextNode.nextNode;
//        }
//        return root;
        if (root == null) {
            return root;
        }
        Node prev = null, current = root, next = root.nextNode;
        while (next != null) {
            if (odd) {
                current.nextNode = next.nextNode;
            }
            prev = current;
            current = current.nextNode;
            next = current.nextNode;
        }
        return root;
    }

    private void testReverseOfLinkedList() {
        Scanner scanner = new Scanner(System.in);
        Node root = getLinkedListFromInput(scanner);
        printLinkedList(root);
        Node reversedRoot = reverseLinkedListIteratively(root);
        printLinkedList(reversedRoot);
    }

    private void testRemoveKthNode() {
        Scanner scanner = new Scanner(System.in);
        Node root = getLinkedListFromInput(scanner);
        int K = scanner.nextInt();
        printLinkedList(root);
        root = removeKthNodeRecursively(root, K);
        printLinkedList(root);
    }

    private void testRemoveOddNodes() {
        Scanner scanner = new Scanner(System.in);
        Node root = getLinkedListFromInput(scanner);
        printLinkedList(root);
        root = removeOddNodesRecursively(root, true);
        printLinkedList(root);
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //singleLinkedList.testReverseOfLinkedList();
        //singleLinkedList.testRemoveKthNode();
        singleLinkedList.testRemoveOddNodes();
    }
}