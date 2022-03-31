package math.basics;

public class PrimeNumber {
    /**
     * Prime Numbers: 2, 3, 5, 7, 13, ....
     * <p>
     * What is a prime number?
     * A number divisible only by itself and 1
     * Total number divisors = 2
     * </p>
     */

    //Check if a number is prime
    /*
    if the numbers between (1, n) can divide n: it is NOT a Prime
     */
    boolean isPrime_1(int n) { // Bruteforce solution: O(n)
        if (n < 2) return false;
        for (int i = 2; i < n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    boolean isPrime_2(int n) { // Optimised solution: O(sqrt(n))
        /*
        Example: n = 36
        1 x 36
        2 x 18
        3 x 12
        4 x 9
        ------------
        6 x 6 -> Hence only make check till Math.sqrt(n)
        ------------ Pattern repeats as above (so we can ignore)
        9 x 4
        12 x 3
        18 x 2
        36 x 1
         */
        if (n < 2) return false;
        //for (int i = 0; i < Math.sqrt(n); i++) {
        //                  or
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private int cancelAllMultiples(int currentNumber,
                                   int endNumber,
                                   boolean[] isNotPrime) {
        int noOfCancellations = 0;
        for (int i = 2; currentNumber * i <= endNumber; i++) {
            if (!isNotPrime[currentNumber * i]) {
                isNotPrime[currentNumber * i] = true;
                noOfCancellations++;
            }
        }
        return noOfCancellations;
    }
    
    int[] getPrimeNumbersSieveOfEratosthenes(int n) { // O(n * log(log(n)))
        if (n < 2) return new int[0];
        boolean[] isNotPrime = new boolean[n + 1];
        int cancellations = 0;
        for (int i = 2; i * i <= n; i++) {
            if (!isNotPrime[i])
                cancellations += cancelAllMultiples(i, n, isNotPrime);
        }
        int totalPrimeNumbers = n - cancellations - 1; // (n+1) - cancellations - 2;
        int[] primeNumbers = new int[totalPrimeNumbers];
        for (int i = 2, index = 0; i <= n; i++) {
            if (!isNotPrime[i]) primeNumbers[index++] = i;
        }
        return primeNumbers;
    }

    private static void testPrimeNumbers_1() { // O(n * n)
        PrimeNumber primeNumber = new PrimeNumber();
        for (int i = 0; i <= 37; i++) {
            if (primeNumber.isPrime_1(i)) {
                System.out.print(i + " ");
            }
        }
    }

    private static void testPrimeNumbers_2() { // O(n * sqrt(n))
        PrimeNumber primeNumber = new PrimeNumber();
        for (int i = 0; i <= 37; i++) {
            if (primeNumber.isPrime_2(i)) {
                System.out.print(i + " ");
            }
        }
    }

    private static void testPrimeNumbers_3() {
        PrimeNumber primeNumber = new PrimeNumber();
        int n = 37;
        int[] primeNumbers = primeNumber.getPrimeNumbersSieveOfEratosthenes(n);
        for (var prime : primeNumbers) {
            System.out.print(prime + " ");
        }
    }
    
    public static void main(String[] args) {
        //testPrimeNumbers_1();
        //testPrimeNumbers_2();
        testPrimeNumbers_3();
    }
}
