// Min Add to Make Parentheses Valid
// Difficulty: MediumAccuracy: 67.25%Submissions: 8K+Points: 4
// You are given a string s consisting only of the characters '(' and ')'. Your task is to determine the minimum number of parentheses (either '(' or ')') that must be inserted at any positions to make the string s a valid parentheses string.

// A parentheses string is considered valid if:

// Every opening parenthesis '(' has a corresponding closing parenthesis ')'.
// Every closing parenthesis ')' has a corresponding opening parenthesis '('.
// Parentheses are properly nested.
// Examples:

// Input: s = "(()("
// Output: 2
// Explanation: There are two unmatched '(' at the end, so we need to add two ')' to make the string valid.
// Input: s = ")))"
// Output: 3
// Explanation: Three '(' need to be added at the start to make the string valid.
// Input: s = ")()()"
// Output: 1 
// Explanation: The very first ')' is unmatched, so we need to add one '(' at the beginning.
// Constraints:
// 1 ≤ s.size() ≤ 10^5

class Solution {
  public int minParentheses(String s) {
    // Stack to keep track of unmatched parentheses
    Stack<Character> st = new Stack<>();

    // Traverse each character in the string
    for (char ch : s.toCharArray()) {

      // If current char is closing parenthesis ')'
      if (ch == ')') {
        // Check if there is a matching '(' on top of stack
        if (!st.isEmpty() && st.peek() == '(') {
          st.pop(); // Found a match, so remove '('
        } else {
          st.push(ch); // No match, push ')' into stack
        }
      }
      // If it's an opening parenthesis '('
      else {
        st.push(ch);
      }
    }

    // The size of the stack = number of unmatched parentheses
    return st.size();
  }
}
