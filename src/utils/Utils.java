package utils;

import java.util.Arrays;

public class Utils {

    public static String matrixToString(int[][] matrix) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\n");
        for (int[] row : matrix) {
            stringBuilder.append(Arrays.toString(row));
            stringBuilder.append("\n");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
