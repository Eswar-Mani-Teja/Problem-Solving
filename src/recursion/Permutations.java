package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutations {

    public <T> List<List<T>> getAllPermutations(List<T> list) {
        return getAllPermutations(new ArrayList<T>(), list);
    }

    private <K, T extends List<K>> void printAllPermutations(T list, T unprocessed) {
        if (unprocessed.isEmpty()) {
            System.out.println(list);
            return;
        }
        for (var unprocessedElement : unprocessed) {
            var processedList = new ArrayList<>(list);
            var unprocessedList = new ArrayList<>(unprocessed);
            processedList.add(unprocessedElement);
            unprocessedList.remove(unprocessedElement);
            printAllPermutations(processedList, unprocessedList);
        }
    }

    private <T> List<List<T>> getAllPermutations(List<T> list,
                                                 List<T> unprocessed) {
        if (unprocessed.isEmpty()) {
            List<List<T>> permutations = new ArrayList<>();
            permutations.add(list);
            return permutations;
        }
        List<List<T>> result = new ArrayList<>();
        for (var unprocessedElement : unprocessed) {
            var processedList = new ArrayList<>(list);
            var unprocessedList = new ArrayList<>(unprocessed);
            processedList.add(unprocessedElement);
            unprocessedList.remove(unprocessedElement);
            result.addAll(getAllPermutations(processedList, unprocessedList));
        }
        return result;
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    private void permutate(int[] arr, int start) {
        if (arr.length == start) {
            System.out.println(Arrays.toString(arr));
            return;
        }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            permutate(arr, start + 1);
            swap(arr, i, start);
        }
    }

    private static void testPermutations() {
        Permutations permutations = new Permutations();
        permutations.printAllPermutations(new ArrayList<>(),
                Arrays.asList(1, 2, 3));
        System.out.println("---");
        permutations.printAllPermutations(new ArrayList<>(),
                Arrays.asList("a", "b", "c"));
        System.out.println("---");
        permutations.getAllPermutations(
                Arrays.asList(5, 6, 7)).forEach(System.out::println);
        System.out.println("---");
        permutations.getAllPermutations(
                Arrays.asList("x", "y", "z")).forEach(System.out::println);
        System.out.println("---");
        permutations.permutate(new int[] {4, 5, 6}, 0);
    }

    public static void main(String[] args) {
        testPermutations();
    }
}
