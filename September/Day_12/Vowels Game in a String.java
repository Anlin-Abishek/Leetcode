class Solution {
    public boolean doesAliceWin(String s) {
         for(int i=0;i<s.length(); i++)
            switch(s.charAt(i))
            {
                case 'a', 'e', 'i', 'o', 'u' -> {return true;}
                default -> {}
            }

        return false;
        
    }
}
