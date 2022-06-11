
// Test 9: 10:30-12:30
// 1: https://leetcode.com/problems/palindrome-number/   
class Solution {
    public boolean isPalindrome(int x) {
        if(x<0)return false;
        int xrev=0;
        int xinitial=x;
        while(x!=0){
            xrev=xrev*10+(x%10);
            x/=10;
        }
        
        if(xrev==xinitial)return true;
        return false;
        
    }
}

// 2: https://leetcode.com/problems/reverse-linked-list-ii/     
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
//leetcode.com/problems/reverse-linked-list-ii/
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null||head.next==null)return head;
        
        ListNode top=new ListNode(-1);
        top.next=head;
        ListNode pre=top;
        for(int i=0;i<left-1;i++){
            pre=pre.next;
        }
        ListNode start=pre.next;
        ListNode then=start.next;
        
        //draw in white board to visualize it...
        for(int i=0;i<right-left;i++){
            start.next=then.next;
            then.next=pre.next;
            pre.next=then;
            then=start.next;
        }
        
        return top.next;
            
    }
}
// 3: https://leetcode.com/problems/cheapest-flights-within-k-stops/description/ class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] adj=new int[n][n];
        
        for(int[] flight:flights){
            adj[flight[0]][flight[1]]=flight[2];
            
        }
        
        int[] dist=new int[n];
        int[] currStops=new int[n];
        
        int inf=Integer.MAX_VALUE;
        
        Arrays.fill(dist,inf);
        Arrays.fill(currStops,inf);
        
        dist[src]=0;
        currStops[src]=0;
        
        //{node,cost,stops}
        PriorityQueue<int[]> pq=new PriorityQueue<int[]>((a,b)->a[1]-b[1]);
        pq.add(new int[]{src,0,0});
        
        while(!pq.isEmpty()){
            
            int[] curr=pq.poll();
            int from=curr[0],cost=curr[1],stops=curr[2];
            
            if(from==dst){return cost;}
            if(stops==k+1){continue;}
            
            for(int to=0;to<n;to++){
                
                if(adj[from][to]>0){
                    
                    int d=adj[from][to]+cost;
                    
                    if(d<dist[to]){
                        
                        pq.add(new int[]{to,d,stops+1});
                        dist[to]=d;
                        currStops[to]=stops;
                        
                    }else if(stops<currStops[to]){
                        //because an intermediate node with optimized cost may result in more stops than required.So we are also taking the paths having less stopage which may fetch result in the future
                        
                        pq.add(new int[]{to,d,stops+1});
                        
                    }
                    
                }
            }
        }
        return dist[dst]==inf?-1:dist[dst];
        
    }
}

// 4: https://www.geeksforgeeks.org/largest-area-rectangular-sub-matrix-equal-number-1s-0s/
