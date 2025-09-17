/*
 * Decode the string
Difficulty: MediumAccuracy: 44.28%Submissions: 64K+Points: 4Average Time: 10m
Given an encoded string s, decode it by expanding the pattern k[substring], where the substring inside brackets is written k times. k is guaranteed to be a positive integer, and encodedString contains only lowercase english alphabets. Return the final decoded string.

Note: The test cases are generated so that the length of the output string will never exceed 105 .

Examples:

Input: s = "3[b2[ca]]"
Output: "bcacabcacabcaca"
Explanation:
Inner substring “2[ca]” breakdown into “caca”.
Now, new string becomes “3[bcaca]”
Similarly “3[bcaca]” becomes “bcacabcacabcaca” which is final result.
Input: s = "3[ab]"
Output: "ababab"
Explanation: The substring "ab" is repeated 3 times giving "ababab".
Constraints:
1 ≤ |s| ≤ 10^5 
 */

class Solution {
  static String decodeString(String s) {
    int n = s.length();
    Stack<String> st = new Stack<>();

    for (int i = 0; i < n; i++) {
      char ch = s.charAt(i);

      if (ch == ']') {
        StringBuilder temp = new StringBuilder();

        // Pop until '['
        while (!st.peek().equals("[")) {
          temp.insert(0, st.pop()); // prepend popped string
        }
        st.pop(); // remove '['

        int k = Integer.parseInt(st.pop()); // get repeat count

        StringBuilder mul = new StringBuilder();
        for (int j = 0; j < k; j++) {
          mul.append(temp); // append temp k times
        }
        st.push(mul.toString());

      } else if (Character.isDigit(ch)) {
        StringBuilder num = new StringBuilder();
        while (i < n && Character.isDigit(s.charAt(i))) {
          num.append(s.charAt(i));
          i++;
        }
        i--;
        st.push(num.toString());

      } else {
        st.push(String.valueOf(ch));
      }
    }

    StringBuilder ans = new StringBuilder();
    while (!st.isEmpty()) {
      ans.insert(0, st.pop());
    }

    return ans.toString();
  }
}
