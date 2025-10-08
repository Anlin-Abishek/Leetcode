class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];

        for (int i = 0; i < n; i++) {
            long spellStrength = spells[i];

            int low = 0;
            int high = m - 1;
            int firstSuccessfulIndex = m;

            while (low <= high) {
                int mid = low + (high - low) / 2;
                long product = spellStrength * potions[mid];

                if (product >= success) {
                    firstSuccessfulIndex = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            pairs[i] = m - firstSuccessfulIndex;
        }

        return pairs;
        
    }
}
