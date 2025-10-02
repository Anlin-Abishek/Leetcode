class Solution {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int res=numBottles;
        for(int e=numBottles;e>=numExchange;numExchange++){
            res++;
            e-=numExchange-1;
        }
        return res;
    }
}
