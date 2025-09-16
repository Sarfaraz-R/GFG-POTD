// Postfix Evaluation
// Difficulty: MediumAccuracy: 63.04%Submissions: 123K+Points: 4
// You are given an array of strings arr[] that represents a valid arithmetic
// expression written in Reverse Polish Notation (Postfix Notation). Your task
// is to evaluate the expression and return an integer representing its value.

// Note: A postfix expression is of the form operand1 operand2 operator (e.g.,
// "a b +").
// And the division operation between two integers always computes the floor
// value, i.e floor(5 / 3) = 1 and floor(-5 / 3) = -2.
// It is guaranteed that the result of the expression and all intermediate
// calculations will fit in a 32-bit signed integer.

// Examples:

// Input: arr[] = ["2", "3", "1", "*", "+", "9", "-"]
// Output: -4
// Explanation: If the expression is converted into an infix expression, it will
// be 2 + (3 * 1) – 9 = 5 – 9 = -4.
// Input: arr[] = ["2", "3", "^", "1", "+"]
// Output: 9
// Explanation: If the expression is converted into an infix expression, it will
// be 2 ^ 3 + 1 = 8 + 1 = 9.
// Constraints:
// 3 ≤ arr.size() ≤ 103
// arr[i] is either an operator: "+", "-", "*", "/" or "^", or an integer in the
// range [-10^4, 10^4]

class Solution {
  public int evaluatePostfix(String[] arr) {
    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < arr.length; i++) {
      char ch = arr[i].charAt(0);
      if ((ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^') && arr[i].length() == 1) {
        int res = performOperation(st, ch);
        // System.out.println(res);
        st.push(res);
      } else {
        int num = Integer.valueOf(arr[i]);
        st.push(num);
      }

    }
    return st.peek();
  }

  private int performOperation(Stack<Integer> st, char ch) {
    int a = st.pop();
    int b = st.pop();
    switch (ch) {
      case '+':
        return a + b;
      case '-':
        return b - a;
      case '*':
        return a * b;
      case '/':
        return (int) Math.floorDiv(b, a);
      case '^':
        return (int) Math.pow(b, a);
      default:
        return 0;
    }
  }

}