// Test 15:

// 1: https://leetcode.com/problems/climbing-stairs   
class Solution {
    
    public int climbStairs(int n) {
        
        // int dp[]=new int[n+1];
        // dp[0]=1;
        // dp[1]=1;
        // for(int i=2;i<=n;i++){
        //     dp[i]=dp[i-1]+dp[i-2];
        // }
        // return dp[n];
        int a=1,b=1,c=n;
        while(n-->1){
            c=a+b;
            a=b;
            b=c;
        }
        return c;
        
        
    }
    // public int help(int n){
    //     if(n<0){
    //         return 0;
    //     }
    //     if(n<=1){
    //         return 1;
    //     }
    //     if(dp[n]!=null){
    //         return dp[n];
    //     }
    //     return dp[n]=help(n-1)+help(n-2);
    // }
}


// 2: https://leetcode.com/problems/maximal-square/  
class Solution {
    public int maximalSquare(char[][] matrix) {
         int m=matrix.length;
        int n=matrix[0].length;
        int a=0;
        for(int i=0;i<m;i++){
            a=Math.max(a,matrix[i][0]-'0');
        }
        for(int j=0;j<n;j++){
            a=Math.max(a,matrix[0][j]-'0');
        }
        
       
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                  if(matrix[i][j]=='1'){
                      matrix[i][j]+=Math.min(Math.min(s(i-1,j,matrix),s(i,j-1,matrix)),s(i-1,j-1,matrix));
                a=Math.max(a,matrix[i][j]-'0');
                  }
                
               
                
            }
            
        }
        return a*a;
    }
    
    int s(int i,int j,char[][] matrix){
        
        return matrix[i][j]-'0';
    }
}

// 3: https://leetcode.com/problems/evaluate-division/  










// 4: https://leetcode.com/problems/remove-boxes/ 

//Topdown O(n^3)
class Solution {
    //Explanation:https://leetcode.com/problems/remove-boxes/discuss/101310/Java-top-down-and-bottom-up-DP-solutions
    Integer[][][] dp;
    public int removeBoxes(int[] boxes) {
        int n=boxes.length;
        dp=new Integer[n+1][n+1][n+1];
        return solve(0,n-1,0,boxes);
    }
    //k =same color boxes as boxes[l] contiguous to left of boxes[l]...
    
    public int solve(int l,int r,int k,int[] boxes){
        if(l>r){
            return 0;
        }
        if(dp[l][r][k]!=null){
            return dp[l][r][k];
        }
        //storing the initial variables 
        int i=l;
        int j=r;
        int t=k;
        
        while(i+1<=j&&boxes[i+1]==boxes[i]){
            ++i;
            ++t;
        }
        
        int ans=(t+1)*(t+1)+solve(i+1,r,0,boxes);
        //eg: 2 2 2 3 4 2 2
        //one possible ans is deleting the twos ...remaining : 3 4 2 2 ..
        //so previous 2's are removed..t+1=3 for three 2's
        
        
        //other possible answer is deleting 3 and 4 pehle ...then remaining: 2 2 2 2 2 and then deleting all the twos
        //before deleting 3 and 4 we had three 2's before 3->4->2->2
        
        //for 3->4 ...k=0 ..since previous boxes of different colors..
        //for 2->2-> 2- - ->(3- - -> 4- - ->) 2->2
        //for the 2 after 4--> we have 3 twos' before 3..
        //so same color boxes for 2 after four is t+1=3
        for(int m=i+1;m<=j;m++){
            if(boxes[m]==boxes[i]){
                ans=Math.max(ans,solve(i+1,m-1,0,boxes)+solve(m,j,t+1,boxes));
            }
        }
        return dp[l][r][k]=ans;
        
    }
}

//bottom up(O(n^4))
class Solution {
    public int removeBoxes(int[] boxes) {
        int n=boxes.length;
        int[][][] dp=new int[n+1][n+1][n+1];
        
        for(int i=0;i<n;i++){
            for(int k=0;k<=i;k++){
                dp[i][i][k]=(1+k)*(1+k);
            }
            
        }
        
        for(int len=1;len<n;len++){
            for(int j=len;j<n;j++){
                int i=j-len;
                for(int k=0;k<=i;k++){
                    int ans=(1+k)*(1+k)+dp[i+1][j][0];
                    for(int m=i+1;m<=j;m++){
                    if(boxes[m]==boxes[i]){
                        ans=Math.max(ans,dp[i+1][m-1][0]+dp[m][j][k+1]);
                    }
                        
                }
                    dp[i][j][k]=ans;
                }
                
                
            }
        }
        return dp[0][n-1][0];
    }
}
