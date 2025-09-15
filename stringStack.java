/*
 You are given two strings pat and tar consisting of lowercase English characters. You can construct a new string s by performing any one of the following operation for each character in pat:

Append the character pat[i] to the string s.
Delete the last character of s (if s is empty do nothing).
After performing operations on every character of pat exactly once, your goal is to determine if it is possible to make the string s equal to string tar.

Examples:

Input: pat = "geuaek", tar = "geek"
Output: true
Explanation: Append the first three characters of pat to s, resulting in s = "geu". Delete the last character for 'a', leaving s = "ge". Then, append the last two characters 'e' and 'k' from pat to s, resulting in s = "geek", which matches tar.
Input: pat = "agiffghd", tar = "gfg"
Output: true
Explanation: Skip the first character 'a' in pat. Append 'g' and 'i' to s, resulting in s = "gi". Delete the last character for 'f', leaving s = "g". Append 'f', 'g', and 'h' to s, resulting in s = "gfgh". Finally, delete the last character for 'd', leaving s = "gfg", which matches tar.
Input: pat = "ufahs", tar = "aus"
Output: false
Explanation: It is impossible to construct the string tar from pat with the given operations.
Constraints:
1 ≤ pat.size(), tar.size() ≤ 10^5


 */

class Solution {
  // Function to check if 'tar' can be formed from 'pat'
  // using a stack-like matching rule:
  // 1. Start comparing from the end of both strings.
  // 2. If characters match move both pointers left.
  // 3. If characters don’t match skip 2 chars in 'pat' (like popping in stack).
  // 4. If all characters of 'tar' are matched → return true.
  public boolean stringStack(String pat, String tar) {
    int n = pat.length(); // length of source string
    int m = tar.length(); // length of target string
    int i = n - 1; // pointer for pat (start from last char)
    int j = m - 1; // pointer for tar (start from last char)

    // Traverse until either pat or tar is fully processed
    while (i >= 0 && j >= 0) {
      if (pat.charAt(i) == tar.charAt(j)) {
        // If chars match move both i and j left
        i--;
        j--;
      } else {
        // If mismatch skip 2 chars in pat
        // This simulates a "stack pop" effect
        i -= 2;
      }
    }

    // If j < 0 all characters of tar matched successfully
    return j < 0;
  }
}
