// Test 7: 
// 1: https://leetcode.com/problems/happy-number/

class Solution {
    HashMap<Integer,Integer> mp;
    public boolean isHappy(int n) {
        mp=new HashMap<>();
        return isHappyReally(n);
    }
    public boolean isHappyReally(int n) {
        if(n==0)return false;
        if(n==1){
            return true;
        }
        else if(mp.getOrDefault(n,0)==2)return false;
        String s=String.valueOf(n);
        int k=s.length();
        if(Math.pow(10,k-1)-n==0)return true;
        else{
            mp.put(n,2);
            n=0;
            
            for(char c:s.toCharArray()){
                n+=((c-'0')*(c-'0'));
            }
            return isHappyReally(n);
        }
        
    }
}

// 2: https://leetcode.com/problems/sum-of-subarray-minimums/   

// 3: https://leetcode.com/problems/snakes-and-ladders/ 

// 4: https://leetcode.com/problems/burst-balloons/
class Solution {
    //Approach check in note book
    Integer[][] dp;
    public int maxCoins(int[] nums) {
        int[] new_nums=new int[nums.length+2];
        int n=1;
        for(int a:nums) if(a>0)new_nums[n++]=a;
        new_nums[0]=new_nums[n++]=1;
        dp=new Integer[n][n];
        solve(new_nums,0,n-1);
        return dp[0][n-1];
         
    }
    public int solve(int[] arr,int left,int right){
        if(left==right-1)return 0;
        else if(dp[left][right]!=null)return dp[left][right];
        int ans=0;
        for(int i=left+1;i<right;i++){
            ans=Math.max(ans,arr[left]*arr[i]*arr[right]+solve(arr,left,i)+solve(arr,i,right));
            
        }
        return dp[left][right]=ans;
    }
}
