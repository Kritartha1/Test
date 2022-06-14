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
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n=sc.nextInt();
	    int[] arr=new int[n];
	    for(int i=0;i<n;i++){
	    arr[i]=sc.nextInt();
	    }
	    Solution s=new Solution();
	    s.radix_sort(arr);
	    System.out.println(Arrays.toString(arr));
	}
}
class Solution{
    public static void radix_sort(int[] arr){
    int n=arr.length;
        int max=Max(arr,n);
        
        for(int exp=1;max/exp>0;exp*=10){
           
            count_sort(arr,exp,n);
        }
        
    }
    
    static void count_sort(int[] arr,int exp,int n){
    int[] temp=new int[10];
    
    for(int i=0;i<n;i++){
    temp[(arr[i]/exp)%10]++;
    }
    for(int i=1;i<10;i++){
    temp[i]+=temp[i-1];
    }
    int[] ans=new int[n];
    for(int i=n-1;i>=0;i--){
    int pos=temp[(arr[i]/exp)%10]-1;
    ans[pos]=arr[i];
    temp[(arr[i]/exp)%10]--;

    }
    for(int i=0;i<n;i++){
    arr[i]=ans[i];
    }
    
    
    
    }
    static int Max(int[] arr,int n){
        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
        max=Math.max(max,arr[i]);
        }
        return max;
    }
    
}




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
