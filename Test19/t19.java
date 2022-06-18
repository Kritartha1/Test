// 1: https://leetcode.com/problems/valid-square/ 
(square : sides equal and diagonals equal)..
points A,B,C,D of square Collections.sort(l,(a,b)->{return a[0]==b[0]?a[1]-b[1]:a[0]-b[0];})
  /////
  
  class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        ArrayList<int[]> l=new ArrayList<>();
        l.add(p1);
        l.add(p2);
        l.add(p3);
        l.add(p4);
        Collections.sort(l,(a,b)->{return a[0]==b[0]?a[1]-b[1]:a[0]-b[0];});
        
        double a=side(l.get(0),l.get(1));
        double b=side(l.get(0),l.get(2));
        double c=side(l.get(2),l.get(3));
        double d=side(l.get(1),l.get(3));
        
        double diag1=side(l.get(0),l.get(3));
        double diag2=side(l.get(1),l.get(2));
        
        boolean sidesEqual=(a!=0)&&(a==b)&&(b==c)&&(c==d)&&(diag1==diag2);
        //no need to check d==a bcoz if a==b and b==c and c==d, obviously d==a will be true and it will be a square if it's diagonals of same length..
        
        return sidesEqual;
       
        
    }
   
    double side(int[] a,int[] b){
        double x=(b[0]-a[0])*(b[0]-a[0]);
        double y=(a[1]-b[1])*(a[1]-b[1]);
        return (x+y);
    }
}
  

  
// 2: https://practice.geeksforgeeks.org/problems/rotten-oranges2536/1   
class Solution
{
    //Function to find minimum time required to rot all oranges. 
    boolean isSafe(int[][] grid,int r,int c){
        return r>=0&&c>=0&&r<grid.length&&c<grid[0].length&&grid[r][c]==1;
    }
    public int orangesRotting(int[][] grid)
    {
        // Code here
        int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
        int fresh=0;
        int rot=0;
        int time=0;
        int m=grid.length;
        int n=grid[0].length;
        Queue<int[]> q=new LinkedList<>();
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]==2){
                    q.add(new int[]{i,j});
                    rot++;
                }else if(grid[i][j]==1){
                    fresh++;
                }
            }
        }
        if(fresh==0){
            return 0;
        }
        
        
        while(!q.isEmpty()){
            int size=q.size();
            while(size--!=0){
                int[] curr=q.poll();
                for(int i=0;i<4;i++){
                    int r=curr[0]+dir[i][0];
                    int c=curr[1]+dir[i][1];
                    if(isSafe(grid,r,c)){
                        grid[r][c]=2;
                        q.add(new int[]{r,c});
                        --fresh;
                        
                    }
                }
            }
            ++time;
            
            
        }
        
        return fresh==0?time-1:-1;
        
        
        
    }
}


// 3: https://leetcode.com/problems/redundant-connection/    
class Solution {
    //Time Complexity: O(N*alpha(N)) ==>approx O(N)  bcoz O(α(N))≈≈O(1),
    int[] parent;
    
    //for more time efficiency , do rank transformation...
    public int[] findRedundantConnection(int[][] edges) {
        int n=edges.length;
        
        parent=new int[n];
        Arrays.fill(parent,-1);
        
        for(int[] edge:edges){
            int from=find(edge[0]-1);
            int to=find(edge[1]-1);
            if(from==to){
               
                return edge;
            }else{
                parent[from]=to;
            }
        }
        return new int[]{0,0};
        
    }
    
    int find(int v){
        if(parent[v]==-1){
            return v;
        }
        
        return parent[v]=find(parent[v]);
    }
    
}
