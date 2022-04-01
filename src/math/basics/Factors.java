package math.basics;

public class Factors {

    private void factors_1(int n) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
    }

    private static void testFactorsOfANumber(Factors factors) {
        factors.factors_1(55);
    }

    public static void main(String[] args) {
        testFactorsOfANumber(new Factors());
    }
}
