class Solution {
    
         int m;
    private int n;
    private int[][] heights;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        this.heights = heights;

        boolean[][] pacificReachable = new boolean[m][n];
        boolean[][] atlanticReachable = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            dfs(i, 0, pacificReachable, Integer.MIN_VALUE);
            dfs(i, n - 1, atlanticReachable, Integer.MIN_VALUE);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, pacificReachable, Integer.MIN_VALUE);
            dfs(m - 1, j, atlanticReachable, Integer.MIN_VALUE);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacificReachable[i][j] && atlanticReachable[i][j]) {
                    List<Integer> coordinate = new ArrayList<>();
                    coordinate.add(i);
                    coordinate.add(j);
                    result.add(coordinate);
                }
            }
        }

        return result;
    }

    private void dfs(int r, int c, boolean[][] reachable, int prevHeight) {
        if (r < 0 || r >= m || c < 0 || c >= n || reachable[r][c] || heights[r][c] < prevHeight) {
            return;
        }

        reachable[r][c] = true;

        for (int[] dir : directions) {
            dfs(r + dir[0], c + dir[1], reachable, heights[r][c]);
        }
    }
}
    
