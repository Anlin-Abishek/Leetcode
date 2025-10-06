class Solution {
    private int n;
    private int[][] grid;
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        this.n = grid.length;
        this.grid = grid;
        
        int low = 0;
        int high = n * n - 1;
        int minTime = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(mid)) {
                minTime = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return minTime;
    }

    private boolean canReach(int t) {
        if (grid[0][0] > t || grid[n - 1][n - 1] > t) {
            return false;
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.add(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

            if (r == n - 1 && c == n - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int newR = r + dir[0];
                int newC = c + dir[1];

                if (newR >= 0 && newR < n && newC >= 0 && newC < n && !visited[newR][newC] && grid[newR][newC] <= t) {
                    visited[newR][newC] = true;
                    queue.add(new int[]{newR, newC});
                }
            }
        }
        return false;
    }
}
