import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;
    long[][][][] dp;
    boolean[][][][] vis;
    long[][] powNum, C;
    int n, m, k;
    int[] nums;

    public int magicalSum(int m, int k, int[] nums) {
        this.m = m; this.k = k; this.nums = nums;
        n = nums.length;
        powNum = new long[n][m + 1];
        for (int i = 0; i < n; i++) {
            powNum[i][0] = 1;
            for (int j = 1; j <= m; j++)
                powNum[i][j] = powNum[i][j - 1] * nums[i] % MOD;
        }

        // build nCk table
        C = new long[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            C[i][0] = C[i][i] = 1;
            for (int j = 1; j < i; j++)
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
        }

        dp = new long[55][35][35][35];
        vis = new boolean[55][35][35][35];
        return (int) dfs(0, 0, 0, 0);
    }

    long dfs(int pos, int carry, int used, int ones) {
        if (pos == n) {
            int extra = Integer.bitCount(carry);
            return (used == m && ones + extra == k) ? 1 : 0;
        }
        if (vis[pos][carry][used][ones]) return dp[pos][carry][used][ones];
        vis[pos][carry][used][ones] = true;
        long ans = 0;
        for (int cnt = 0; cnt + used <= m; cnt++) {
            int total = carry + cnt;
            int bit = total & 1;
            int ncarry = total >> 1;
            long sub = dfs(pos + 1, ncarry, used + cnt, ones + bit);
            if (sub == 0) continue;
            long ways = C[m - used][cnt];
            long prod = powNum[pos][cnt];
            ans = (ans + sub * ways % MOD * prod) % MOD;
        }
        return dp[pos][carry][used][ones] = ans;
    }
}
