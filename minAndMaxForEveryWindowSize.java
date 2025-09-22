// Max of min for every window size
// Difficulty: HardAccuracy: 42.9%Submissions: 75K+Points: 8Average Time: 45m
// You are given an integer array arr[], the task is to find the maximum of minimum values for every window size k where 1≤ k ≤ arr.size().

// For each window size k, consider all contiguous subarrays of length k, determine the minimum element in each subarray, and then take the maximum among all these minimums.

// Return the results as an array, where the element at index i represents the answer for window size i+1.

// Examples :

// Input: arr[] = [10, 20, 30, 50, 10, 70, 30]
// Output: [70, 30, 20, 10, 10, 10, 10] 
// Explanation: 
// Window size 1: minimums are [10, 20, 30, 50, 10, 70, 30], maximum of minimums is 70.
// Window size 2: minimums are [10, 20, 30, 10, 10, 30], maximum of minimums is 30.
// Window size 3: minimums are [10, 20, 10, 10, 10], maximum of minimums is 20.
// Window size 4–7: minimums are [10, 10, 10, 10], maximum of minimums is 10.
// Input: arr[] = [10, 20, 30]
// Output: [30, 20, 10]
// Explanation: 
// Window size 1: minimums of  [10], [20], [30], maximum of minimums is 30.
// Window size 2: minimums of [10, 20], [20,30], maximum of minimums is 20.
// Window size 3: minimums of [10,20,30], maximum of minimums is 10.
// Constraints:
// 1 ≤ arr.size() ≤ 105
// 1 ≤ arr[i] ≤ 106

class Solution {
  public ArrayList<Integer> maxOfMins(int[] arr) {
    int n = arr.length;
    Stack<Integer> st = new Stack<>();

    // PSE
    int[] pse = new int[n];
    for (int i = 0; i < n; i++) {
      while (!st.isEmpty() && arr[st.peek()] >= arr[i])
        st.pop();
      pse[i] = st.isEmpty() ? -1 : st.peek();
      st.push(i);
    }

    st.clear();

    // NSE
    int[] nse = new int[n];
    for (int i = n - 1; i >= 0; i--) {
      while (!st.isEmpty() && arr[st.peek()] >= arr[i])
        st.pop();
      nse[i] = st.isEmpty() ? n : st.peek();
      st.push(i);
    }

    // Fill window[]
    int[] window = new int[n];
    Arrays.fill(window, Integer.MIN_VALUE);

    for (int i = 0; i < n; i++) {
      int len = nse[i] - pse[i] - 1;
      window[len - 1] = Math.max(window[len - 1], arr[i]);
    }

    // Propagate backwards
    for (int len = n - 2; len >= 0; len--) {
      window[len] = Math.max(window[len], window[len + 1]);
    }

    // Convert to ArrayList
    ArrayList<Integer> ans = new ArrayList<>();
    for (int val : window)
      ans.add(val);

    return ans;
  }
}
