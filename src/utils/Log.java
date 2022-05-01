package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Log {

    private static final Map<String, Integer> counter = new HashMap<>();
    public static boolean currentCallFlag = false;

    public static void callCounter() {
        callCounter("", 3);
    }

    public static void callCounter(String tag) {
        callCounter(tag, 3);
    }

    private static void callCounter(String tag, int callPosition) {
        String methodName = Thread.currentThread().getStackTrace()[callPosition].getMethodName()
                + "#" + tag;
        if (counter.containsKey(methodName)) {
            int count = counter.get(methodName);
            counter.put(methodName, count + 1);
        } else {
            counter.put(methodName, 1);
        }
        if (currentCallFlag)
            System.out.println(methodName + ": " + counter.get(methodName));
    }

    public static void callSummary(String tag) {
        Set<String> keys = counter.keySet();
        for (String key : keys) {
            if (key.endsWith(tag)) {
                System.out.println("Total " + counter.get(key) +
                        " calls made with " + tag);
                break;
            }
        }
    }

    public static void callSummaries() {
        if (!counter.isEmpty())
            System.out.println("------------\nCall Summary\n------------");
        for (var entry : counter.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
