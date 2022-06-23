// Test 22: 
// 1.	https://leetcode.com/problems/unique-binary-search-trees-ii/  
class Solution {
   public List<TreeNode> generateTrees(int n) {
        return helper(1,n);
        // return ans;
        
    }
    public List<TreeNode> helper(int l,int r) {
        List<TreeNode> temp=new ArrayList<TreeNode>();
        if(l>r){
            temp.add(null);
            return temp;
        }
        
        for(int i=l;i<=r;i++){
            List<TreeNode> l_LIST=helper(l,i-1);
            List<TreeNode> r_LIST=helper(i+1,r);
            for(TreeNode L:l_LIST){
                for(TreeNode R:r_LIST){
                    
                    TreeNode root=new TreeNode(i);
                    root.left=L;
                    root.right=R;
                    temp.add(root);
                     
                }
            }   
        }
        
        return temp;
        
    }
     
}


// 2.	https://leetcode.com/problems/coin-change/    
//Slowest recursion:
class Solution {
    int n;
    long[][] dp;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        n=coins.length;
        dp=new long[n+1][amount+1];
        for(long[] row:dp){
            Arrays.fill(row,-1);
        }
        int ans=(int)helper(coins,0,amount);
        return ans==Integer.MAX_VALUE?-1:ans;
        
    }
    public long helper(int[] coins,int i,int amount){
        if(amount==0){
            return 0;
        }
        if(i>=n){
            return Integer.MAX_VALUE;
        }
        if(dp[i][amount]!=-1){
            return dp[i][amount];
        }
        
        if(amount<coins[i]){
            return dp[i][amount]=Integer.MAX_VALUE;
        }
        long ans=Math.min(1+helper(coins,i,amount-coins[i]),helper(coins,i+1,amount));
        return dp[i][amount]=ans;
        
    }
}


// 3.	https://leetcode.com/problems/swim-in-rising-water/  
//Check the binary search approach here:https://leetcode.com/problems/swim-in-rising-water/discuss/1285099/Easy-Solution-w-Explanation-or-Optimization-from-Brute-Force-to-Binary-Search-or-Beats-100
class Solution {
    public int swimInWater(int[][] grid) {
        int n=grid.length;
        
        PriorityQueue<int[]> q=new PriorityQueue<int[]>((a,b)->{return a[2]-b[2];});
        q.add(new int[]{0,0,grid[0][0]});
        // int initial=g[0][1];
        int max=0;
        int[][] dir={{0,1},{0,-1},{1,0},{-1,0}};
        
        while(!q.isEmpty()){
            int[] curr=q.poll();
            max=Math.max(max,curr[2]);
            grid[curr[0]][curr[1]]=-1;
            if(curr[0]==n-1&&curr[1]==n-1){
                break;
            }
            
            for(int i=0;i<4;i++){
                int r=curr[0]+dir[i][0];
                int c=curr[1]+dir[i][1];
                if(r>=0&&c>=0&&r<n&&c<n&&grid[r][c]!=-1){
                    q.add(new int[]{r,c,grid[r][c]});
                }
            }
            
            
        }
        return max;
    }
}


// 4.	https://practice.geeksforgeeks.org/problems/topological-sort/1  
class Solution
{
    //Function to return list containing vertices in Topological order. 
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) 
    {
        // add your code here
        int[] indeg=new int[V];
        int[] outdeg=new int[V];
        int i=0;
        
        for(ArrayList<Integer> edge:adj){
            for(int to:edge){
               
                indeg[to]++;
            }
            
            
        }
        i=0;
        Queue<Integer> q=new LinkedList<>();
        for(;i<V;i++){
            if(indeg[i]==0){
                q.add(i);
            }
        }
        i=0;
        int[] ans=new int[V];
        
        
        while(!q.isEmpty()){
            int curr=q.poll();
            ans[i++]=curr;
            
            for(int neighbour:adj.get(curr)){
                
                  indeg[neighbour]--;
                  if(indeg[neighbour]==0){
                    q.add(neighbour);
                
                }
                
            }
        }
        return ans;
    }
}
