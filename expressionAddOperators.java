// Expression Add Operators
// Difficulty: HardAccuracy: 61.49%Submissions: 31K+Points: 8Average Time: 40m
// Given a string s that contains only digits (0-9) and an integer target, return all possible strings by inserting the binary operator ' + ', ' - ', and/or ' * ' between the digits of s such that the resultant expression evaluates to the target value.

// Note:

// Operands in the returned expressions should not contain leading zeros. For example, 2 + 03 is not allowed whereas 20 + 3 is fine.
// It is allowed to not insert any of the operators.
// Driver code will print the final list of strings in lexicographically smallest order.
// Examples:

// Input: s = "124", target = 9
// Output: ["1+2*4"]
// Explanation: The valid expression that evaluate to 9 is 1 + 2 * 4
// Input: s = "125", target = 7
// Output: ["1*2+5", "12-5"]
// Explanation: The two valid expressions that evaluate to 7 are 1 * 2 + 5 and 12 - 5.
// Input: s = "12", target = 12
// Output: ["12"] 
// Explanation: s itself matches the target. No other expressions are possible.
// Input: s = "987612", target = 200
// Output: []
// Explanation: There are no expressions that can be created from "987612" to evaluate to 200.
// Constraints:
// 1 ≤ s.size() ≤ 9
// s consists of only digits (0-9).
// -2^31 ≤ target ≤ 2^31-1

class Solution {
  public void solve(String s, int target, int idx, long currVal, long prevOpr, String expression,
      ArrayList<String> result) {
    if (idx == s.length()) {
      if (currVal == target) {
        result.add(expression);
      }
      return;
    }

    for (int i = idx; i < s.length(); i++) {
      // Skip numbers with leading zero
      if (i > idx && s.charAt(idx) == '0') {
        break;
      }

      long currNum = Long.parseLong(s.substring(idx, i + 1));

      if (idx == 0) {
        // First number, no operator needed
        solve(s, target, i + 1, currNum, currNum, expression + currNum, result);
      } else {
        // Addition
        solve(s, target, i + 1, currVal + currNum, currNum, expression + "+" + currNum, result);

        // Subtraction
        solve(s, target, i + 1, currVal - currNum, -currNum, expression + "-" + currNum, result);

        // Multiplication (handle precedence)
        long newVal = currVal - prevOpr + (prevOpr * currNum);
        solve(s, target, i + 1, newVal, prevOpr * currNum, expression + "*" + currNum, result);
      }
    }
  }

  public ArrayList<String> findExpr(String s, int target) {
    ArrayList<String> result = new ArrayList<>();
    solve(s, target, 0, 0, 0, "", result);
    return result;
  }
}
