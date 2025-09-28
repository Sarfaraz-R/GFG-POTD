// Longest Bounded-Difference Subarray
// Difficulty: MediumAccuracy: 58.27%Submissions: 24K+Points: 4
// Given an array of positive integers arr[] and a non-negative integer x, the task is to find the longest sub-array where the absolute difference between any two elements is not greater than x.
// If multiple such subarrays exist, return the one that starts at the smallest index.

// Examples:

// Input: arr[] = [8, 4, 5, 6, 7], x = 3 
// Output: [4, 5, 6, 7] 
// Explanation: The sub-array described by index [1..4], i.e. [4, 5, 6, 7]
// contains no two elements whose absolute differnce is greater than 3.
// Input: arr[] = [1, 10, 12, 13, 14], x = 2 
// Output: [12, 13, 14] 
// Explanation: The sub-array described by index [2..4], i.e. [12, 13, 14]
// contains no two elements whose absolute differnece is greater than 2. 
// Constraints:
// 1 ≤ arr.size() ≤ 105
// 1 ≤ arr[i] ≤ 10^9
// 0 ≤ x ≤ 10^9

class Solution {
  public ArrayList<Integer> longestSubarray(int[] arr, int x) {
    int n = arr.length;
    int left = 0;
    int right = 0;
    Deque<Integer> min = new LinkedList<>();
    Deque<Integer> max = new LinkedList<>();
    int maxLen = 0;
    int index = 0;
    while (right < n) {
      while (!min.isEmpty() && arr[min.peekLast()] > arr[right]) {
        min.pollLast();
      }
      while (!max.isEmpty() && arr[max.peekLast()] < arr[right]) {
        max.pollLast();
      }
      min.offerLast(right);
      max.offerLast(right);
      while (arr[max.peekFirst()] - arr[min.peekFirst()] > x) {
        if (left == max.peekFirst())
          max.pollFirst();
        if (left == min.peekFirst())
          min.pollFirst();
        left++;
      }
      int len = right - left + 1;
      if (len > maxLen) {
        maxLen = len;
        index = left;
      }
      right++;
    }
    ArrayList<Integer> ans = new ArrayList<>();
    for (int i = index; i < index + maxLen; i++) {
      ans.add(arr[i]);
    }
    return ans;

  }
}
