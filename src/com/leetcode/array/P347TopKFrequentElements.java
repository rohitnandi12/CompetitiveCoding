package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/top-k-frequent-elements/description/">347. Top K Frequent Elements</a>
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
 * Example 1:
 * Input: nums = [1,1,1,2,2,3], k = 2
 * Output: [1,2]
 * Example 2:
 * Input: nums = [1], k = 1
 * Output: [1]
 * Constraints:
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * k is in the range [1, the number of unique elements in the array].
 * It is guaranteed that the answer is unique.
 * Follow up: Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * Level:Medium
 */
public class P347TopKFrequentElements {

    public int[] topKFrequent(int[] nums, int k) {
        /*List<Integer>[] bucket = new List[nums.length + 1];
	    Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

	    for (int n : nums) {
	    	frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
	    }

	    for (int key : frequencyMap.keySet()) {
	    	int frequency = frequencyMap.get(key);
	    	if (bucket[frequency] == null) {
	    		bucket[frequency] = new ArrayList<>();
	    	}
	    	bucket[frequency].add(key);
	    }

	    int[] res = new int[k];
        int idx = 0;

	    for (int pos = bucket.length - 1; pos >= 0 && idx < k; pos--) {
	    	if (bucket[pos] != null) {
                for (int n: bucket[pos]) {
                    if (idx == k) break;
                    res[idx++] = n;
                }
	    	}
	    }
	    return res;*/
        int min = nums[0];
        int max = nums[0];
        int n = nums.length;
        for(int i=1; i < n; i++){
            if(nums[i] < min) min = nums[i];
            if(nums[i]> max) max = nums[i];
        }

        int[] freq = new int[max-min+1];

        for(int num: nums)
            freq[num-min]++;

        List<Integer>[] bucket = new ArrayList[n+1];

        int l =0;
        for(int i=0; i < freq.length; i++){
            if(bucket[freq[i]] == null) bucket[freq[i]] = new ArrayList<>();
            bucket[freq[i]].add(i+min);
            if(freq[i] > l) l = freq[i];
        }
        int[] ans = new int[k];
        int j = 0;
        for(int i = l; i>0; i--){
            if(bucket[i] != null){
                for (Integer d: bucket[i]) {
                    ans[j++] = d;
                    if (j == k) break;
                }
            }
            if(j==k) break;
        }

        return ans;
    }
}
