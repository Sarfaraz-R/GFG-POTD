/*
 * Max rectangle
Difficulty: HardAccuracy: 36.43%Submissions: 121K+Points: 8Average Time: 35m
You are given a 2D binary matrix mat[ ][ ], where each cell contains either 0 or 1. Your task is to find the maximum area of a rectangle that can be formed using only 1's within the matrix.

Examples:

Input: mat[][] = [[0, 1, 1, 0],
                [1, 1, 1, 1],
                [1, 1, 1, 1],
                [1, 1, 0, 0]]
Output: 8
Explanation: The largest rectangle with only 1’s is from (1, 0) to (2, 3) which is
[1, 1, 1, 1]
[1, 1, 1, 1]
and area is 4 * 2 = 8.
Input: mat[][] = [[0, 1, 1],
                [1, 1, 1],
                [0, 1, 1]]
Output: 6
Explanation: The largest rectangle with only 1’s is from (0, 1) to (2, 2) which is
[1, 1]
[1, 1]
[1, 1]
and area is 2 * 3 = 6.
Constraints:
1 ≤ mat.size(), mat[0].size() ≤1000
0 ≤ mat[][] ≤1
 */

class Solution {
  static int maxArea(int mat[][]) {
    int n = mat.length;
    int m = mat[0].length;
    int[][] dp = new int[n][m];

    for (int i = n - 1; i >= 0; i--) {
      for (int j = m - 1; j >= 0; j--) {
        if (mat[i][j] == 1) {
          if (i == n - 1) {
            dp[i][j] = 1;
          } else {
            dp[i][j] = 1 + dp[i + 1][j];
          }
        }
      }
    }

    int[][] nse = new int[n][m];
    int[][] pse = new int[n][m];

    for (int i = 0; i < n; i++) {
      computeNSE(dp, i, nse);
      computePSE(dp, i, pse);
    }
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int length = nse[i][j] - pse[i][j] - 1;
        int bredth = dp[i][j];
        maxArea = Math.max(length * bredth, maxArea);
      }
    }

    return maxArea;

  }

  private static void computeNSE(int[][] dp, int i, int[][] nse) {
    Stack<Integer> st = new Stack<>();
    int m = dp[i].length;
    for (int j = m - 1; j >= 0; j--) {
      while (!st.isEmpty() && dp[i][st.peek()] >= dp[i][j]) {
        st.pop();
      }
      nse[i][j] = st.isEmpty() ? m : st.peek();
      st.push(j);
    }
  }

  private static void computePSE(int[][] dp,int i, int[][] pse){
        Stack<Integer> st=new Stack<>();
        int m=dp[i].length;
        for(int j=0;j<m;j++){
            while(!st.isEmpty()&&dp[i][st.peek()]>=dp[i][j]){
                st.pop();
            }
            pse[i][j]=st.isEmpty()?-1:st.peek();
            st.push(j);
        }
    }