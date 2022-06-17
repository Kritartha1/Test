// Test 18: 19:05-20:45 
// 1: https://leetcode.com/problems/minimum-path-sum/     
class Solution {
    
    public int minPathSum(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        for(int i=1;i<m;i++){
           grid[i][0]+=grid[i-1][0]; 
        }
        for(int j=1;j<n;j++){
           grid[0][j]+=grid[0][j-1]; 
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                grid[i][j]+=Math.min(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }
}

// 2: https://leetcode.com/problems/decode-ways/  
//Approach 1: recursive
class Solution {
    
    boolean isAlphabet(String s,int i,int j){
        boolean a=(j<=s.length());
        if(a){
            int num=Integer.parseInt(s.substring(i,j));
            return (num>0)&&(num<27);
        }else{
            return false;
        }
    }
    int[] dp;
    public int numDecodings(String s) {
        dp=new int[s.length()];
        Arrays.fill(dp,-1);
        return decode(s,0);
    }
    
    int decode(String s,int i){
        if(i>=s.length()){
            return 1;
        }
        
        if(s.charAt(i)=='0'){
            return 0;
        }
        if(dp[i]!=-1){
            return dp[i];
        }
        
        int ans=decode(s,i+1)+(isAlphabet(s,i,i+2)?decode(s,i+2):0);
        return dp[i]=ans;
    }
}

//Approach 2: iterative +faster
class Solution {
    public int numDecodings(String s) {
        
        int n=s.length();
        
        int a=1;//valid decoding starting from index (i+1)
        int b=0;//valid decoding starting from index (i+2)
        int temp=0;
        for(int i=n-1;i>=0;i--){
            temp=0;
            if(s.charAt(i)!='0'){
                temp=a;
                if((i<n-1)&&(s.charAt(i)=='1'||s.charAt(i)=='2'&&s.charAt(i+1)<'7')){
                    temp+=b;
                }
               
            }
            b=a;
            a=temp;
        }
        
        return a;
        
    }
}

// 3: https://leetcode.com/problems/as-far-from-land-as-possible/   
//Approach 1:bfs
class Solution {
    boolean isSafe(int[][] grid,int r,int c){
        return r>=0&&c>=0&&r<grid.length&&c<grid[0].length&&(grid[r][c]!=1);
    }
    public int maxDistance(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==1){
                    q.add(new int[]{i,j});
                }
            }
        }
        
        if(q.isEmpty()||q.size()==m*n){
            return -1;
            
        }
        int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
        int ans=0;
        while(!q.isEmpty()){
            int size=q.size();
            while(size--!=0){
                int[] curr=q.poll();
                
                for(int i=0;i<4;i++){
                    int r=curr[0]+dir[i][0];
                    int c=curr[1]+dir[i][1];
                    if(isSafe(grid,r,c)){
                        grid[r][c]=1;//making grids as vis using grid[i][j]=1.
                        q.add(new int[]{r,c});
                    }
                    
                }
            }
            
            ++ans;
        }
        return ans-1;//bcoz code will run one extra time for the last 0...so we have to substract 1 from ans..
        //eg: [1,0]..ans=0
        //first bfs will run for index 0...then index 1 will be inserted..ans=ans+1=1
        //then bfs for index 1....ans=ans+1=2...now q is empty..
    }
}

//Approach 2: dp
class Solution {
    public int maxDistance(int[][] grid) {
        int ans=-1;
        int n=grid.length;
        int m=grid[0].length;
        
        
        //
        //Let's divide the problem into smaller subproblems..
        //to get to grid[i][j], we can either enter through (i-1,j) or (i,j-1) or (i+1,j) or (i,j+1)...
        //first we are iterating from the top left (0,0) to the bottom right (n-1,m-1)..
        //and checking for the path (i-1,j) and (i,j-1) for each (i,j)..if a shorter path from either of the mentioned indices exists..
        //if so update the distance..
        //also
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]==1){
                    grid[i][j]=0;
                    continue;
                }
                grid[i][j]=201;
                //there can be maximum 100 rows as per the constraints given..
                //so maximum manhattan dist <=2*100..
                //if there is no 1's in the grid, 201 will act as INT_MAX..
                //if we found after all the iterations are done, the we can say that there are no 1's ..so return -1
                if(i>0){
                    grid[i][j]=Math.min(grid[i-1][j]+1,grid[i][j]);
                }
                if(j>0){
                    grid[i][j]=Math.min(grid[i][j-1]+1,grid[i][j]);
                }
            }
        }
        
        for(int i=n-1;i>=0;i--){
            for(int j=m-1;j>=0;j--){
                
                if(grid[i][j]==0){
                    continue;
                }
                
                if(i<n-1){
                    grid[i][j]=Math.min(grid[i+1][j]+1,grid[i][j]);
                }
                if(j<m-1){
                    grid[i][j]=Math.min(grid[i][j+1]+1,grid[i][j]);
                }
                
                ans=Math.max(ans,grid[i][j]);
            }
        }
        
        return ans==201?-1:ans;
        
        
    }
}



// 4: https://www.geeksforgeeks.org/water-jug-problem-using-bfs/   
//Explanation: https://www.geeksforgeeks.org/water-jug-problem-using-bfs/


import java.io.*;
import java.util.*;


//states: 

//1.fill jug 1 and keep jug 2 as it is.
//2.fill jug 2 and keep jug 1 as it is.

//1.empty jug 1 and keep jug 2 as it is.
//1.empty jug 2 and keep jug 1 as it is.

//1.fill jug 2 from jug 1.
//1.fill jug 1 from jug 2.
class GFG {
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int m=sc.nextInt();
	    int n=sc.nextInt();
	    int d=sc.nextInt();
	    
	    Solution s=new Solution();
	    s.waterJug(m,n,d);
	    
	    
	}
	
}
class Solution{
    static class Pair{
        int j1;
        int j2;
        ArrayList<Pair> path;
        Pair(int j1,int j2){
            this.j1=j1;
            this.j2=j2;
            this.path=new ArrayList<>();
        }
        Pair(int j1,int j2,ArrayList<Pair> prevPath){
            this.j1=j1;
            this.j2=j2;
            this.path=new ArrayList<>();
            path.addAll(prevPath);
            path.add(new Pair(this.j1,this.j2));
        }
    }
    
    public static void waterJug(int m,int n,int target){
        boolean[][] vis=new boolean[m+1][n+1];
        Pair initial=new Pair(0,0);
        initial.path.add(initial);
        Queue<Pair> q=new LinkedList<>();
        q.add(initial);
        while(!q.isEmpty()){
            Pair curr=q.poll();
            
            if(curr.j1>m||curr.j2>n||vis[curr.j1][curr.j2]){
                continue;
            }
            vis[curr.j1][curr.j2]=true;
            if(curr.j1==target||curr.j2==target){
                 if(curr.j1==target){
                    if(curr.j2==0){
                        //we already reached (d,0)--so we only need to print the path
                    }else{
                        //state (d,y)..
                        //so empty jug 2===(d,0)
                        curr.path.add(new Pair(curr.j1,0));
                        
                    }
                 }else{
                    if(curr.j1==0){
                        //we already reached (0,d)--so we only need to print the path
                    }else{
                        //state (x,d)..
                        //so empty jug 2===(0,d)
                        curr.path.add(new Pair(0,curr.j2));
                        
                    }
                }
                
                //print the path
                for(int i=0;i<curr.path.size();i++){
                    System.out.print("{"+curr.path.get(i).j1+","+curr.path.get(i).j2+"}");
                }
                return;
            }
            //empty a jug and keep the other jug as it is..
            q.add(new Pair(curr.j1,0,curr.path));
            q.add(new Pair(0,curr.j2,curr.path));
            
            //fill a jug and keep the other jug as it is..
            q.add(new Pair(curr.j1,n,curr.path));
            q.add(new Pair(m,curr.j2,curr.path));
            
            //fill from jug 2 to jug 1
            int fill=Math.min(n-curr.j2,curr.j1);
            q.add(new Pair(curr.j1-fill,curr.j2+fill,curr.path));
            
            //fill from jug 1 to jug 2
            fill=Math.min(m-curr.j1,curr.j2);
            q.add(new Pair(curr.j1+fill,curr.j2-fill,curr.path));
            
            
            
            
        }
        
    }
    
    

}
