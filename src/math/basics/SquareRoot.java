package math.basics;

public class SquareRoot {

    int getSquareRoot(int n) {
        int start = 0;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid * mid == n)
                return mid;
            if (mid * mid < n) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return end; //As end is moving to lower value (ex: 15.xxx rather than start which overshoots)
    }

    double getSquareRoot(int n, int precision) {
        double squareRoot = getSquareRoot(n);
        double increment = 0.1;
        for (int i = 0; i <= precision; i++) {
            while (squareRoot * squareRoot < n) {
                squareRoot = squareRoot + increment;
            }
            squareRoot -= increment; // To undo the last increment (as it overflows)
            increment *= 0.1;
        }
        return squareRoot;
    }

    private static void testSquareRoot(SquareRoot squareRoot) {
        int n = 226;
        int precision = 2;
        double sqrt = squareRoot.getSquareRoot(n, precision);
        System.out.println("Square root as Int: " + squareRoot.getSquareRoot(n));
        System.out.println("Precision: " + precision);
        System.out.println("Calculated: " + sqrt);
        System.out.println("Actual: " + Math.sqrt(n));
        System.out.printf("Precision print: %." + precision + "f", sqrt);
    }

    public static void main(String[] args) {
        testSquareRoot(new SquareRoot());
    }
}
