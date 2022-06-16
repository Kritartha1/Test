// Test 16: 12:20:1:30
// 1: https://leetcode.com/problems/max-points-on-a-line/   
class Solution {
    public int maxPoints(int[][] points) {
        int n=points.length;
        
        double[][] grad=new double[n][n];
        int max=0;
        for(int i=0;i<n;i++){
            HashMap<Double,Integer> mp=new HashMap<>();
            double slope=0;
            for(int j=i+1;j<n;j++){
                // if(i==j){
                //     slope=Integer.MAX_VALUE;
                //     continue;
                // }
                if(points[j][0]==points[i][0]){
                    slope=Integer.MAX_VALUE;
                    //bcoz slope of 90deg and slope of -90 deg is a same line.
                }else if(points[j][1]==points[i][1]){
                    slope=0.0;
                    //this condition is given bcoz java double also has 
                }
                else{
                    slope=(double)(points[j][1]-points[i][1])/(points[j][0]-points[i][0]);
                    
                }
              
                max=Math.max(mp.getOrDefault(slope,0)+1,max);
                mp.put(slope,mp.getOrDefault(slope,0)+1);
                
            }
            
        }
        
        
        return max+1;//+1 to count the point itself
    }
}
// 2: https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/  
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    Node prev=null;
    public Node flatten(Node head) {
        
        return helper(head);
        
    }
    public Node helper(Node head){
        Node curr=head;
        while(curr!=null){
            
            prev=curr;
            Node Next=curr.next;
            if(curr.child!=null){
                
                Node temp=helper(curr.child);//temp =head of the flatten child list..
                curr.next=temp;//current node .next will be it's flatten child node
                temp.prev=curr;//temp node's prev node must be the curr node.
                prev.next=Next;//temp node's rightmost or end node's next node should be Next node..which was originally curr node's next...
                curr.child=null;//also given:  The nodes in the list must have all of their child pointers set to null
                
                if(Next!=null){
                   Next.prev=prev;//if originally curr node's next=null, then Next node can't have a prev value..
                }
                
                curr=Next;//now current node will be the Next node for iteration..
                
            }else{
                curr=curr.next;
            }
            
        }
        return head;
        
    }
}

// 3: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/   

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    TreeNode R=new TreeNode();//right most node of the linkedlist
    public void flatten(TreeNode root) {
        flattenHelper(root);
        
    }
     public TreeNode flattenHelper(TreeNode root) {
        if(root==null){
            return root ;
        }
         if(root.left==null&&root.right==null){
             R=root;
             return root;
        }
         TreeNode left=root.left;
         TreeNode right=root.right;
         TreeNode l=flattenHelper(left);
        
        
        if(l!=null){
            root.right=l;
            root.left=null;
            
        }
            R.right=flattenHelper(right);
            root.left=null;
        
        return root;
        
    }
}
