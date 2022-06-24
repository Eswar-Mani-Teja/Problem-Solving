package recursion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSum {

    public static void main(String[] args) {
        int[] input = new int[]{3, 1, 2};
        List<Integer> subsetSums = new ArrayList<>();
        getAllSubsetSums(input, 0, 0, subsetSums); // O(2^n)
        Collections.sort(subsetSums); // O(log(2^n)
        System.out.println(subsetSums);
    }

    // O(2^n)
    private static void getAllSubsetSums(int[] input,
                                         int index,
                                         int sum,
                                         List<Integer> subsetSums) {
        if (index == input.length) {
            subsetSums.add(sum);
            return;
        }
        getAllSubsetSums(input, index + 1, sum + input[index], subsetSums);
        getAllSubsetSums(input, index + 1, sum, subsetSums);
    }
}
