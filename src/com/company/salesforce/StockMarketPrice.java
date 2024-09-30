package com.company.salesforce;

/**
 * Given n prices = [1, 2, 3, 2] and min_price = 2, max_price = 3
 * Find number of periods for which the minPrice = min_price and maxPrice = max_price;
 * <p>
 * Above input the subArrays [2,3], [2,3,2], [3,2] are the sub-arrays satisfying
 * the above condition.
 */
public class StockMarketPrice {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 2};

        int result = oN2Solution(prices, 2, 3);
        result = oNSolution(new int[]{1, 2, 3, 1, 2, 5}, 2, 3);
        System.out.println(result);
    }

    public static int oNSolution(int[] prices, int minPrice, int maxPrice) {
        int n = prices.length;
        int count = 0;

        int left = 0; // Left pointer of the window
        int minPos = -1, maxPos = -1; // Track the position of last min_price and max_price

        for (int right = 0; right < n; right++) {
            if (prices[right] == minPrice) {
                minPos = right; // Update the last position where we found min_price
            }
            if (prices[right] == maxPrice) {
                maxPos = right; // Update the last position where we found max_price
            }

            // The window is valid if both min_price and max_price are inside the current window
            if (minPos != -1 && maxPos != -1) {
                // The count of valid sub-arrays ending at `right`
                count += Math.min(minPos, maxPos) - left + 1;
            }

            // If the price at `right` exceeds max_price or is lower than min_price, adjust the window
            if (prices[right] < minPrice || prices[right] > maxPrice) {
                left = right + 1;
                minPos = -1; // Reset min position
                maxPos = -1; // Reset max position
            }
        }

        return count;
    }

    public static int oN2Solution(int[] prices, int minPrice, int maxPrice) {
        int N = prices.length;
        int totalPeriods = 0;
        for (int i = 0; i < N; i++) {
            int currMinPrice = prices[i];
            int currMaxPrice = prices[i];
            for (int j = i; j < N; j++) {
                currMinPrice = Math.min(currMinPrice, prices[j]);
                currMaxPrice = Math.max(currMaxPrice, prices[j]);

                if (currMinPrice == minPrice && currMaxPrice == maxPrice) {
                    totalPeriods += 1;
                }
            }
        }

        return totalPeriods;
    }
}
