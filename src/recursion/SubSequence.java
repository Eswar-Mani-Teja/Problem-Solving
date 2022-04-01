package recursion;

import java.util.ArrayList;

public class SubSequence {

    /**
     * ------  ""/abc ----------
     * /                          \
     * a/bc                        ""/bc
     * /      \                    /        \
     * ab/c         a/c             b/c          ""/c
     * /  \          /   \           /  \        /     \
     * abc/""   ab/c   ac/""  a/""    bc/""  b/""   c/""     ""/""
     */

    public void printAllSubsequences(String input) {
        printAllSubsequences("", input);
    }

    public ArrayList<String> getAllSubsequences(String input) {
        return getAllSubsequences("", input);
    }

    private void printAllSubsequences(String processed, String unprocessed) {
        if (unprocessed.isEmpty()) {
            if (!processed.isEmpty())
                System.out.print(processed + " ");
            return;
        }
        printAllSubsequences(processed + unprocessed.charAt(0),
                unprocessed.substring(1));
        printAllSubsequences(processed,
                unprocessed.substring(1));
    }

    private ArrayList<String> getAllSubsequences(String processed, String unprocessed) {
        if (unprocessed.isEmpty()) {
            ArrayList<String> subSequences = new ArrayList<>();
            if (!processed.isEmpty()) {
                subSequences.add(processed);
            }
            return subSequences;
        }
        var left = getAllSubsequences(processed + unprocessed.charAt(0),
                unprocessed.substring(1));
        var right = getAllSubsequences(processed,
                unprocessed.substring(1));
        left.addAll(right);
        return left;
    }

    private static void testSubSequence(SubSequence subSequence) {
        //subSequence.printAllSubsequences("abc");
        ArrayList<String> subSequences = subSequence.getAllSubsequences("abc");
        System.out.println(subSequences);
    }

    public static void main(String[] args) {
        SubSequence subSequence = new SubSequence();
        testSubSequence(subSequence);
    }
}
