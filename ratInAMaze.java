// Rat in a Maze
// Difficulty: MediumAccuracy: 35.75%Submissions: 382K+Points: 4Average Time: 25m
// Consider a rat placed at position (0, 0) in an n x n square matrix maze[][]. The rat's goal is to reach the destination at position (n-1, n-1). The rat can move in four possible directions: 'U'(up), 'D'(down), 'L' (left), 'R' (right).

// The matrix contains only two possible values:

// 0: A blocked cell through which the rat cannot travel.
// 1: A free cell that the rat can pass through.
// Your task is to find all possible paths the rat can take to reach the destination, starting from (0, 0) and ending at (n-1, n-1), under the condition that the rat cannot revisit any cell along the same path. Furthermore, the rat can only move to adjacent cells that are within the bounds of the matrix and not blocked.
// If no path exists, return an empty list.

// Note: Return the final result vector in lexicographically smallest order.

// Examples:

// Input: maze[][] = [[1, 0, 0, 0], [1, 1, 0, 1], [1, 1, 0, 0], [0, 1, 1, 1]]
// Output: ["DDRDRR", "DRDDRR"]
// Explanation: The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.
// Input: maze[][] = [[1, 0], [1, 0]]
// Output: []
// Explanation: No path exists as the destination cell (1, 1) is blocked.
// Input: maze[][] = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
// Output: ["DDRR", "RRDD"]
// Explanation: The rat has two possible paths to reach the destination: DDRR and RRDD.
// Constraints:
// 2 ≤ n ≤ 5
// 0 ≤ maze[i][j] ≤ 1

class Solution {
  public ArrayList<String> ratInMaze(int[][] maze) {
    ArrayList<String> ans = new ArrayList<>();
    int n = maze.length;
    int[] dx = { 1, 0, 0, -1 };
    int[] dy = { 0, -1, 1, 0 };
    char[] direction = { 'D', 'L', 'R', 'U' };
    boolean[][] vis = new boolean[n][n];
    vis[0][0] = true;
    backTrack(maze, 0, 0, new StringBuilder(), n, ans, dx, dy, direction, vis);
    return ans;

  }

  private void backTrack(int[][] maze, int row, int col, StringBuilder sb, int n, ArrayList<String> ans, int[] dx,
      int[] dy, char[] direction, boolean[][] vis) {
    if (row == n - 1 && col == n - 1) {
      ans.add(sb.toString());
      return;
    }

    for (int k = 0; k < 4; k++) {
      int nr = row + dx[k];
      int nc = col + dy[k];
      if (nr < n && nr >= 0 && nc >= 0 && nc < n && maze[nr][nc] == 1 && !vis[nr][nc]) {
        sb.append(direction[k]);
        vis[nr][nc] = true;
        backTrack(maze, nr, nc, sb, n, ans, dx, dy, direction, vis);
        sb.deleteCharAt(sb.length() - 1);
        vis[nr][nc] = false;
      }
    }

  }
}