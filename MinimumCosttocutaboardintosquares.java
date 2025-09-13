/*
 * Minimum Cost to cut a board into squares
Difficulty: MediumAccuracy: 60.83%Submissions: 22K+Points: 4
Given a board of dimensions n × m that needs to be cut into n*m squares. The cost of making a cut along a horizontal or vertical edge is provided in two arrays:

x[]: Cutting costs along the vertical edges (length-wise).
y[]: Cutting costs along the horizontal edges (width-wise).
Find the minimum total cost required to cut the board into squares optimally.

Examples:

Input: n = 4, m = 6, x[] = [2, 1, 3, 1, 4], y[] = [4, 1, 2]
Output: 42
Explanation:

Initially no. of horizontal segments = 1 & no. of vertical segments = 1.
Optimal way to cut into square is:
• Pick 4 (from x) -> vertical cut, Cost = 4 × horizontal segments = 4,
  Now, horizontal segments = 1, vertical segments = 2.
• Pick 4 (from y) -> horizontal cut, Cost = 4 × vertical segments = 8,
  Now, horizontal segments = 2, vertical segments = 2.
• Pick 3 (from x) -> vertical cut, Cost = 3 × horizontal segments = 6,
  Now, horizontal segments = 2, vertical segments = 3.
• Pick 2 (from x) -> vertical cut, Cost = 2 × horizontal segments = 4,
  Now, horizontal segments = 2, vertical segments = 4.
• Pick 2 (from y) -> horizontal cut, Cost = 2 × vertical segments = 8,
  Now, horizontal segments = 3, vertical segments = 4.
• Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 3,
  Now, horizontal segments = 3, vertical segments = 5.
• Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 3,
  Now, horizontal segments = 3, vertical segments = 6.
• Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 6,
  Now, horizontal segments = 4, vertical segments = 6.
So, the total cost = 4 + 8 + 6 + 4 + 8 + 3 + 3 + 6 = 42.
Input: n = 4, m = 4, x[] = [1, 1, 1], y[] = [1, 1, 1]
Output: 15
Explanation: 

Initially no. of horizontal segments = 1 & no. of vertical segments = 1.
Optimal way to cut into square is: 
• Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 1,
  Now, horizontal segments = 2, vertical segments = 1.
• Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 1,
  Now, horizontal segments = 3, vertical segments = 1.
• Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 1,
  Now, horizontal segments = 4, vertical segments = 1.
• Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 4,
  Now, horizontal segments = 4, vertical segments = 2.
• Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 4,
  Now, horizontal segments = 4, vertical segments = 3.
• Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 4,
  Now, horizontal segments = 4, vertical segments = 4
So, the total cost = 1 + 1 + 1 + 4 + 4 + 4 = 15.
Constraints:
2 ≤ n, m ≤ 10^3
1 ≤ x[i], y[j] ≤10^3
 */
















import java.util.Arrays;

class Solution {
    public static int minCost(int n, int m, int[] x, int[] y) {
        
        // Sort the cost arrays in ascending order
        Arrays.sort(x);
        Arrays.sort(y);
        
        // Start from the end (highest costs first)
        int i = m - 2;  // index for vertical cuts (m-1 cuts possible → last index m-2)
        int j = n - 2;  // index for horizontal cuts (n-1 cuts possible → last index n-2)
        
        int minCost = 0;       // Total minimum cost
        int verticalCuts = 1;  // Current number of vertical segments
        int horiCuts = 1;      // Current number of horizontal segments
        
        // Greedy approach: always cut the costliest partition first
        while (i >= 0 && j >= 0) {
            int cost = 0;
            
            // If the vertical cut is costlier or equal, cut vertically
            if (x[i] >= y[j]) {
                cost = x[i] * horiCuts;  // Cost depends on how many horizontal parts exist
                verticalCuts++;          // After cutting, vertical segments increase
                i--;                     // Move to next vertical cut
            } else {
                // Otherwise, cut horizontally
                cost = y[j] * verticalCuts; // Cost depends on how many vertical parts exist
                horiCuts++;                 // After cutting, horizontal segments increase
                j--;                        // Move to next horizontal cut
            }
            
            // Add this cut's cost to total
            minCost += cost;
        }
        
        // If vertical cuts remain
        while (i >= 0) {
            minCost += (x[i] * horiCuts);  // Each vertical cut affects all horizontal segments
            i--;
        }
        
        // If horizontal cuts remain
        while (j >= 0) {
            minCost += (y[j] * verticalCuts);  // Each horizontal cut affects all vertical segments
            j--;
        }
        
        // Return the final minimum cost
        return minCost;
    }
}
