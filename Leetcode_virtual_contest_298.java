//https://leetcode.com/contest/weekly-contest-298/

//1.https://leetcode.com/problems/greatest-english-letter-in-upper-and-lower-case/
class Solution {
    public String greatestLetter(String s) {
        int n=s.length();
        int[] l=new int[52];
        char max='a';
        boolean found=false;
        
        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            
            if(Character.isUpperCase(c)){
                c=Character.toLowerCase(c);
                l[(int)(c-'a')+26]=1;
                // System.out.println("i:"+i+"upper"+((int)(c-'a')+26));
                
            }else{
                l[(int)(c-'a')]=1;
                 // System.out.println("i:"+i+"lower"+((int)(c-'a')));
            }
            
            // System.out.println("hehe"+(l[(int)(c-'a')+26]==l[(int)(c-'a')]));
            
            if( (l[(int)(c-'a')+26]==l[(int)(c-'a')])&&(l[(int)(c-'a')]==1)&&c>=max){
                found=true;
                max=c;;
            }
            
            
        }
        return found?Character.toUpperCase(max)+"":"";
        
    }
}


//2/https://leetcode.com/problems/sum-of-numbers-with-units-digit-k/submissions/
class Solution {

    public int minimumNumbers(int num, int k) {
        if(num==0){
            return 0;
        }
        
        //let sum of n numbers be num with unit place =k.
        //A1+A2+A3+..An=num=(10*a1+A1%10)+(10*a2+A2%10)+..(10*an+An%10)
        //A1+A2+A3+..An=num=(10*a1+10*a2+.....10*an)+(A1%10+A2%10+..)
        //A1+A2+A3+..An=num=(10*a1+10*a2+.....10*an)+(k+k+..)
         //num=(10*a1+10*a2+.....10*an)+n*k
         //num%10=(10*a1+10*a2+.....10*an)%10+n*k%10
         //num%10=0+(n*k)%10
        //check for  (num%10)==(n*k)%10
        //check for n=1 to n=10, bcoz after n=10 (11&k)%10 will be same as 1*k%10..
        //so repetition.
        //also n*k<=num
        
        
        
        for(int n=1;n<=10;n++){
            if((num%10)==(n*k)%10  && n*k<=num){
                return n;
            }
        }
        return -1;
    }
}


//3.https://leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/
class Solution {
    //leetcode.com/problems/longest-binary-subsequence-less-than-or-equal-to-k/discuss/2168423/O(n)
    public int longestSubsequence(String s, int k) {
        int n=s.length();
        int count=0;
        int pow=1;//2^0
        int val=0;
        int count_zero=0;
        int i=n-1;
        
        //getting all the ones from the right to left.
        //ans check for val<=k.
        //if val<=k. Then keep on counting val and incrementing count of 1.
        //else break.
        //from i=0 till breaking index, we need all the zeros.
        //so appending those zero count to count of one.
        for(;i>=0&&val+pow<=k;--i){
            if(s.charAt(i)=='1'){
                val+=pow;
                ++count;
            }else{
                count_zero++;
            }
            pow<<=1;
        }
        for(int j=0;j<=i;j++){
            count_zero+=(s.charAt(j)=='0'?1:0);
        }
        return count_zero+count;
        
        
        
    }
}






//4.https://leetcode.com/problems/selling-pieces-of-wood/

//     Intuition
// Must cut across the entire height or width of the piece,
// this leads up to the dp soluition.


// Explanation
// For a piece of w * h,

// we can make a vertical cut to split it into a * h and (w - a) * h
// So we can update dp[w][h] = max(dp[w][h], dp[a][h] + dp[w - a][h]).

// we can make a horizontal cut to split it into w * a and w * (h - a)
// So we can update dp[w][h] = max(dp[w][h], dp[w][a] + dp[w][h - a]).


// Complexity
// Time O(mmn + mnn)
// Space O(mn)

class Solution {
    public long sellingWood(int m, int n, int[][] prices) {
        long[][] dp=new long[m+1][n+1];
        for(int[] price:prices){
            dp[price[0]][price[1]]=price[2];
        }
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                for(int h=1;h<=i/2;h++){
                    //till i/2 bcoz after i/2 it's redundant..
                    //then bcoz we are checking i-h and h at every step.
                    //after i/2, i-h will be h and h will be i-h..but we need both as a sum..so before i/2, we will get both the pair.
                    dp[i][j]=Math.max(dp[i][j],dp[i-h][j]+dp[h][j]);
                }
                for(int w=1;w<=j/2;w++){
                    dp[i][j]=Math.max(dp[i][j],dp[i][j-w]+dp[i][w]);
                }
            }
        }
        return dp[m][n];
    }
}
