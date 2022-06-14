// Test 14:
// 1: https://leetcode.com/problems/power-of-two/  

class Solution {
    //n&(-n) gives rightmost 1 in n..
    //if n is a power of 2 ,then rightmost 1 must be the MSB..
    
    
    public boolean isPowerOfTwo(int n) {
        
        if(n>0&&(n&(-n))==n)return true;
        return false;
    }
}



// 2: https://www.geeksforgeeks.org/radix-sort/




// 3: https://leetcode.com/problems/binary-tree-maximum-path-sum/   
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
    //Explanation: //leetcode.com/problems/binary-tree-maximum-path-sum/discuss/389609/Full-Explanation-article-with-pseudo-code-Beats-JAVA-100-time-and-100-space-Solution
    
    int max;//will carry the answer
    public int maxPathSum(TreeNode root) {
        max=Integer.MIN_VALUE;
        solve(root);
        return max;
    }
    public int solve(TreeNode root){
        if(root==null){
            return 0;
        }
        
        int l=solve(root.left);
        int r=solve(root.right);
        int lrmax=Math.max(l,r);
        
        int rootNodeMax=Math.max(root.val,lrmax+root.val);
        //toatal max gives the max sum if root is involved 
        int totalMax=Math.max(l+r+root.val,rootNodeMax);
        
        max=Math.max(totalMax,max);//gives overall max and takes into account all the childrens 
        
        //finally it should return a max branch sum...so that root is involved..
        //i.e either the root or the root+leftBranch or root+rightBranch will be the return value for that root....
        return rootNodeMax;
    }
}
