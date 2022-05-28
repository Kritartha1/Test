//Test 2:
// 1.  https://leetcode.com/problems/add-binary/      
// 2. https://www.geeksforgeeks.org/check-reversing-sub-array-make-array-sorted/
// 3.  https://leetcode.com/problems/combination-sum-ii/

// Q1.//
class Solution {
    public String addBinary(String a, String b) {
        int xor=0;
        int carry=0;
         
        int m=a.length()-1;
        int n=b.length()-1;
        int N=Math.max(m,n);
        char[] s=new char[N+1];
        
        while(N>=0){  
            
            int i=m>=0?a.charAt(m--)-'0':0;
            int j=n>=0?b.charAt(n--)-'0':0;
            
            xor=carry^i^j;
            
            s[N]=(char)(xor+'0');
            
            carry=(i&j)^(carry&(i^j));
            
            --N;
           
             
        }
        StringBuilder S=new StringBuilder();
        if(carry==1){
            S.append('1');
            
        }
        S.append(String.valueOf(s));
        return S.toString() ;
        
    }
}

//Q2.//
/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n=sc.nextInt();
	    
	    while(n--!=0){
	        int m=sc.nextInt();
	        int[] arr=new int[m];
	        for(int i=0;i<m;i++){
	            arr[i]=sc.nextInt();
	        }
	        Solution s=new Solution();
	        
	        if(s.solve(arr)){
	            System.out.println("YES");
	        }else{
	            System.out.println("NO");
	        }
	    }
	}
}
class Solution{
    public static boolean solve(int[] arr){
        int[] nums=arr.clone();
        
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(Arrays.toString(arr));
        int i=0;
        int n=arr.length;
        int j=n-1;
        
        
        while(i<j){
            if(arr[i]!=nums[i]&&arr[j]!=nums[j]){
                break;
            }
            if(arr[i]==nums[i]){
                ++i;
            }
            if(arr[j]==nums[j]){
                --j;
            }
        }
        if(i>=j){
            
            return true;
        }
        n=j;
        
        for(;i<=n;i++){
            if(arr[i]!=nums[j]){
                return false;
            }
            --j;
        }
        return true;
    }
}

//Q3.//

class Solution {
   
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans =new ArrayList<>();
        List<Integer> temp=new ArrayList<>();
        Arrays.sort(candidates);
        solve(candidates,target,temp,ans,0);
        return ans;
    }
    public void solve(int[] candidates,int target,List<Integer> temp,List<List<Integer>> ans,int i){
        
        if(target==0){
            List<Integer> k=new ArrayList<Integer>(temp);
            ans.add(k);
            return;
        }
        if(i==candidates.length){
            return;
        }
        
        
        if(candidates[i]>target){
            
            return;
        }
        if(candidates[i]<=target){
            //for the specific index, we have two options...
            //either accept the candidate or move ahead
            
            //accepting the candidate
            temp.add(candidates[i]);
            solve(candidates,target-candidates[i],temp,ans,i+1);
            if(temp.size()>0){
            temp.remove(temp.size()-1);
            }
            ++i;
            
            //bypassing the duplicates
            if(i>0)while(i<candidates.length&&candidates[i]==candidates[i-1]) ++i;
            
            
            //discarding the candidate and moving ahead.
            solve(candidates,target,temp,ans,i);
            
           
        }
        
    }
}
