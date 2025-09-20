/*
 * Longest Subarray Length
Difficulty: MediumAccuracy: 33.32%Submissions: 5K+Points: 4
You are given an array of integers arr[]. Your task is to find the length of the longest subarray such that all the elements of the subarray are smaller than or equal to the length of the subarray.

Examples:

Input: arr[] = [1, 2, 3]
Output: 3
Explanation: The longest subarray is the entire array itself, which has a length of 3. All elements in the subarray are less than or equal to 3.
Input: arr[] = [6, 4, 2, 5]
Output: 0
Explanation: There is no subarray where all elements are less than or equal to the length of the subarray. The longest subarray is empty, which has a length of 0.
Constraints:
1 ≤ arr.size() ≤ 10^5
1 ≤ arr[i] ≤ 10^9
 */

class Solution {
  public static int longestSubarray(int[] arr) {
    Stack<Integer> st = new Stack();
    int n = arr.length;
    int[] pge = new int[n];
    int[] nge = new int[n];
    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
        st.pop();
      }
      pge[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }
    st.clear();
    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
        st.pop();
      }
      nge[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }
    int maxLen = 0;
    for (int i = 0; i < n; i++) {
      int length = nge[i] - pge[i] - 1;
      if (length < arr[i])
        length = 0;
      maxLen = Math.max(length, maxLen);
    }
    return maxLen;

  }
}