class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int con=0;
        while(numBottles>=numExchange){
            con+=numExchange;
            numBottles-=numExchange;
            numBottles++;
        }
        return con+numBottles;

    }
}
