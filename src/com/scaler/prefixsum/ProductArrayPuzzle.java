package com.scaler.prefixsum;

/**
 * Given an array of integers A, find and return the product array of the same size where the ith element of the product array will be equal to the product of all the elements divided by the ith element of the array.
 *
 * Note: It is always possible to form the product array with integer (32 bit) values. Solve it without using the division operator.
 *
 *
 * Input Format
 *
 * The only argument given is the integer array A.
 * Output Format
 *
 * Return the product array.
 * Constraints
 *
 * 2 <= length of the array <= 1000
 * 1 <= A[i] <= 10
 * For Example
 *
 * Input 1:
 *     A = [1, 2, 3, 4, 5]
 * Output 1:
 *     [120, 60, 40, 30, 24]
 *
 * Input 2:
 *     A = [5, 1, 10, 1]
 * Output 2:
 *     [10, 50, 5, 50]
 */
public class ProductArrayPuzzle {
    public ArrayList<Integer> solve(ArrayList<Integer> A) {

        ArrayList<Integer> res = new ArrayList<>();
        // Array         [1, 2, 3, 4, 5]

        // prefixProduct [1, 2, 6, 24, 120]
        // suffixProduct [120, 120, 60, 20, 5]

        // Output        [120, 60, 40, 30, 24]

        //TC = O (N + N + N) SC = O(N);

        ArrayList<Integer> suffixProduct = new ArrayList<>();
        int N = A.size();
        int prod = A.get(N-1);
        suffixProduct.add(prod);
        for(int i=N-2; i>=0; i--) {
            prod *= A.get(i);
            suffixProduct.add(prod);
        }
        Collections.reverse(suffixProduct);
        // suffixProduct.stream().map(i -> i + ", ").forEach(System.out::print);

        prod = 1;
        for(int i=0; i<N; i++) {
            int ansProduct = prod * (i == N-1 ? 1 : suffixProduct.get(i+1));
            prod *= A.get(i);
            res.add(ansProduct);
        }
        return res;

    }
}
