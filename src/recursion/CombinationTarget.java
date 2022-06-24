package recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationTarget {

    public static void main(String[] args) {
        int[] data = new int[]{2, 3, 6, 7};
        int target = 7;

        var result = new ArrayList<ArrayList<Integer>>();
        targetSumCombos(0, data, target, result, new ArrayList<>());
        System.out.print("[REC:PICK/SKIP] Result: ");
        System.out.println(result);

        System.out.println("--------------------------");
        result = new ArrayList<>();
        getCombinationsForTarget(0, data, target, result, new ArrayList<>());
        System.out.print("[REC+ITER] Result: ");
        System.out.println(result);
    }

    private static void targetSumCombos(int index, int[] data, int target, List<ArrayList<Integer>> result, List<Integer> list) {
        if (target == 0) {
            result.add(new ArrayList<>(list));
            return;
        }
        if (target < 0 || index >= data.length) return;

        list.add(data[index]);
        targetSumCombos(index, data, target - data[index], result, list);
        list.remove(list.size() - 1);

        targetSumCombos(index + 1, data, target, result, list);
    }

    private static void getCombinationsForTarget(int index, int[] data, int target,
                                                 List<ArrayList<Integer>> result,
                                                 ArrayList<Integer> subResult) {
        if (target == 0) {
            result.add(new ArrayList<>(subResult));
        }
        if (target < 0) return;
        for (int i = index; i < data.length; i++) {
            subResult.add(data[i]);
            getCombinationsForTarget(i, data, target - data[i], result, subResult);
            subResult.remove(subResult.size() - 1);
        }
    }
}
