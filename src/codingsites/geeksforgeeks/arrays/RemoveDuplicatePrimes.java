package codingsites.geeksforgeeks.arrays;

import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicatePrimes {

    public static void main(String[] args) {
        int[] primes = new int[]{37, 47, 43, 43, 43, 37, 7, 5, 2, 2,
                                3, 41, 11, 13, 37, 2, 3, 43, 11, 5, 5, 17, 5, 2, 41,
                                2, 31, 3, 43, 19, 31, 31, 11, 19, 17, 7, 11, 19, 13,
                                5, 23, 13, 47, 37, 29, 29, 37, 23, 19, 43, 37, 19, 13,
                                47, 23, 2, 41, 11, 2, 37, 37, 37, 17, 47, 3, 37, 5, 7,
                                11, 17, 7, 31};
        System.out.println(Arrays.toString(primes));
        primes = removeDuplicatePrimes(primes);
        System.out.println(Arrays.toString(primes));
    }

    private static int[] removeDuplicatePrimes(int[] primes) {
        // Prime number is divisible by only itself and 1
        // We store product of all the primes.
        // if the product get divisible it is a duplicate prime
        long product = 1L;
        ArrayList<Integer> result = new ArrayList<>();
        for (int prime : primes) {
            if (product % prime != 0) {
                result.add(prime);
                product *= prime;
            }
            // else it is a repeated prime
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
}
