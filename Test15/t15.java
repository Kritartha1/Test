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

//Union find: Best qsn of union find.
class Solution {
    HashMap<String,String> parent=new HashMap<>();
    HashMap<String,Double> val=new HashMap<>();
    
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        
        double[] ans=new double[queries.size()];
        int i=0;
        for(List<String> eqn:equations){
            union(eqn.get(0),eqn.get(1),values[i++]);
        }
        i=0;
        for(List<String> query:queries){
            String a=query.get(0);
            String b=query.get(1);
            
             if(parent.containsKey(a)&&parent.containsKey(b)&&(find(a)==find(b))){
                ans[i++]=val.get(a)/val.get(b);
                 
                 //val.get(a)=a/parent(a)
                 //val.get(b)=b/parent(b)
                 
                 //since parent(a)==parent(b)
                 //So, a/b=val.get(a)/val.get(b)
            }else{
                
                ans[i++]=-1;
            }
        }
        return ans;
    }
    
    public String find(String x){
        String p=parent.get(x);
        //if parent of x is not x,then do path compression and change values of x respectively...
        if(x!=p){
            String pp=find(p);
            parent.put(x,pp);
            val.put(x,val.get(x)*val.get(p));
            
        }
        return parent.get(x);
    }
    
   
    public void add(String x){
         //making sure every node has a parent..
        //if new node is introduced,then it should be parent of itself..
        //and itself to itself ratio=1.

        if(parent.containsKey(x)){
            return ;
        }
        parent.put(x,x);
        val.put(x,1.0);
    }
    
    public void union(String x,String y,double value){
        add(x);add(y);
        String from=find(x);
        String to=find(y);
        parent.put(from,to);
        
        //req=ratio b/w from=(parent of x) and to=(parent of y)
        //x/from=val.get(from)
        //y/to=val.get(to)
        //So,from/to=(x/y)*val.get(y)/val.get(x)=value*val.get(y)/val.get(x)
        
        // 1-->2-->3-->4
        //             |
        //            \ /
        //     7-->6-->5
        //4=parent of 1,  5=parent of 7
        
        //for union (1,7)..=ratio(1/7)=ratio(1/4)*ratio(4/5)*ratio(5/7)
        //ratio(1/4)=val.get(1)=val.get(x)
        //ratio(5/7)=1/ratio(7/5)=1/val.get(7)=1/val.get(y)
        //ratio(4,5)=req.
        
        //So, val.get(x)*req*(1/val.get(y))=ratio(1/7)=value
        //req=ratio(4/5)=value*val.get(y)/val.get(x)
        
        val.put(from,value*val.get(y)/val.get(x));
    }
}








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
