// All Unique Permutations of an array
// Difficulty: MediumAccuracy: 52.85%Submissions: 52K+Points: 4Average Time: 15m
// Given an array arr[] that may contain duplicates. Find all possible distinct permutations of the array in sorted order.
// Note: A sequence A is greater than sequence B if there is an index i for which Aj = Bj for all j<i and Ai > Bi.

// Examples:

// Input: arr[] = [1, 3, 3]
// Output: [[1, 3, 3], [3, 1, 3], [3, 3, 1]]
// Explanation: These are the only possible distinct permutations for the given array.
// Input: arr[] = [2, 3]
// Output: [[2, 3], [3, 2]]
// Explanation: These are the only possible distinct permutations for the given array.
// Constraints:
// 1 ≤ arr.size() ≤ 9

class Solution {
  public static ArrayList<ArrayList<Integer>> uniquePerms(int[] arr) {
    int n = arr.length;
    Arrays.sort(arr);
    boolean[] vis = new boolean[n];
    ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
    permute(ans, new ArrayList<>(), vis, arr);
    return ans;

  }

  private static void permute(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> temp,
      boolean[] vis, int[] arr) {
    if (temp.size() == arr.length) {
      ans.add(new ArrayList<>(temp));
      return;
    }

    for (int i = 0; i < arr.length; i++) {
      // if already used -> skip
      if (vis[i])
        continue;

      // skip duplicates: only allow the first unused duplicate
      if (i > 0 && arr[i] == arr[i - 1] && !vis[i - 1])
        continue;

      vis[i] = true;
      temp.add(arr[i]);
      permute(ans, temp, vis, arr);
      vis[i] = false;
      temp.remove(temp.size() - 1);
    }
  }
}