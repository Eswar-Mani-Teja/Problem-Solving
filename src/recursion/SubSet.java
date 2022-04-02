package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubSet {

    public List<List<Integer>> getAllSubSets(List<Integer> list) {
        return getAllSubSets(new ArrayList<>(), list);
    }

    //Recursive method
    private List<List<Integer>> getAllSubSets(List<Integer> processed,
                                      List<Integer> unprocessed) {
        if (unprocessed.isEmpty()) {
            List<List<Integer>> arrayList = new ArrayList<>();
            if (!processed.isEmpty())
                arrayList.add(processed);
            return arrayList;
        }
        // Process current item
        List<Integer> toProcess = new ArrayList<>(processed);
        toProcess.add(unprocessed.get(0));
        var left = getAllSubSets(toProcess,
                unprocessed.subList(1, unprocessed.size()));
        var right = getAllSubSets(processed,
                unprocessed.subList(1, unprocessed.size()));
        left.addAll(right);
        return left;
    }

    //Iterative method
    private List<List<Integer>> getAllSubSetsIteratively(List<Integer> list) {
        List<List<Integer>> outerList = new ArrayList<>();
        outerList.add(new ArrayList<>());
        for (var currentInteger : list) {
            int outerListSize = outerList.size();
            for (int i = 0; i < outerListSize; i++) {
                var listElement = new ArrayList<>(outerList.get(i));
                listElement.add(currentInteger);
                outerList.add(listElement);
            }
        }
        outerList.remove(0);
        return outerList;
    }

    private static void testSubsets() {
        SubSet subSet = new SubSet();
        var subsets = subSet.getAllSubSetsIteratively(Arrays.asList(1, 2, 3));
        System.out.println(subsets);
    }

    public static void main(String[] args) {
        testSubsets();
    }
}
