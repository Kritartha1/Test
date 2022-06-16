// Test 17:
// 1: https://leetcode.com/problems/flood-fill/   
//simple bfs
class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        
        int[][] directions={{-1,0},{0,1},{1,0},{0,-1}};
        Queue<int[]> q=new LinkedList<>();
        q.add(new int[]{sr,sc});
        boolean[][] vis=new boolean[image.length][image[0].length];
        
        while(!q.isEmpty()){
            int[] curr=q.poll();
            int col=image[curr[0]][curr[1]];
            vis[curr[0]][curr[1]]=true;
            image[curr[0]][curr[1]]=color;
            for(int[] dir:directions){
                int r=curr[0]+dir[0];
                int c=curr[1]+dir[1];
                if(isSafe(r,c,image,col)&&!vis[r][c]){
                    q.add(new int[]{r,c});
                }
            }
        }
        return image;
    }
    boolean isSafe(int r,int c,int[][] image,int col){
        return r>=0&&c>=0&&r<image.length&&c<image[0].length&&(image[r][c]==col);
    }
    
    
}
// 2: https://practice.geeksforgeeks.org/problems/longest-common-substring1452/1  
class Solution{
    int max=0;
    int[][] dp;
    int longestCommonSubstr(String S1, String S2, int n, int m){
        // code here
        dp=new int[n+1][m+1];
        for(int[] row:dp){
            Arrays.fill(row,-1);
        }
        LCS(S1,S2,n,m);
        return max;
        
        // int ans=0;
        // int[][] dp=new int[n+1][m+1];
        // for(int i=1;i<=n;i++){
        //     for(int j=1;j<=m;j++){
        //         if(S1.charAt(i-1)==S2.charAt(j-1)){
        //             dp[i][j]=1+dp[i-1][j-1];
                    
        //         }
        //         ans=Math.max(dp[i][j],ans);
        //     }
        // }
        // return ans;
    }
    
    int LCS(String s1,String s2,int n,int m){
        if(n==0||m==0){
            return 0;
            
        }
        if(dp[n][m]!=-1){
            return dp[n][m];
        }
        int ans=0;
        if(s1.charAt(n-1)==s2.charAt(m-1)){
            ans=1+LCS(s1,s2,n-1,m-1);
            max=Math.max(max,ans);
        }
        LCS(s1,s2,n,m-1);
        LCS(s1,s2,n-1,m);
        return dp[n][m]=ans;
        
    }
}

// 3: https://www.geeksforgeeks.org/detect-negative-cycle-graph-bellman-ford/   

//explanation: Yes+revisit+edge relaxation for(n-1) times..bcoz for a graph having n nodes…the nth node can have atmost (n-1) nodes before it….
//So , relaxation till (n-1) times. Check https://www.youtube.com/watch?v=75yC1vbS8S8   from 18:53 onwards.
class Solution
{
    public int isNegativeWeightCycle(int n, int[][] edges)
    {
        //code here
        int inf=Integer.MAX_VALUE;
        double Dist[]=new double[n];
        Arrays.fill(Dist,inf);
        Dist[0]=0.0;
        for(int v=0;v<n-1;v++){
            for(int[] edge:edges){
                if(Dist[edge[0]]+edge[2]<Dist[edge[1]]){
                    Dist[edge[1]]=Dist[edge[0]]+edge[2];
                }
            }
        }
        for(int v=0;v<n-1;v++){
            for(int[] edge:edges){
                if(Dist[edge[0]]+edge[2]<Dist[edge[1]]){
                    return 1;
                }
            }
        }
        return 0;
        
    }
}



// 4: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/     
