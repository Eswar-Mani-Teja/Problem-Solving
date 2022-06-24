package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class NoDuplicateSubsets {

    public static void main(String[] args) {
        int[] input = new int[]{3, 2, 2, 3, 2, 1};
        List<ArrayList<Integer>> uniqueSubsets = new ArrayList<>();
        Arrays.sort(input);
        getAllTheUniqueSubset(input, 0, new Stack<>(), uniqueSubsets);
        System.out.println(uniqueSubsets);
    }

    private static void getAllTheUniqueSubset(int[] input, int index,
                                              Stack<Integer> subset,
                                              List<ArrayList<Integer>> uniqueSubsets) {
        for (int i = index; i < input.length; i++) {
            // Main Condition: i > 0 and prevItem != currentItem as the list is sorted
            if (i > 0 && input[i] == input[i - 1]) continue;
            subset.push(input[i]);
            getAllTheUniqueSubset(input, i + 1, subset, uniqueSubsets);
            subset.pop();
        }
        uniqueSubsets.add(new ArrayList<>(subset));
    }
}
