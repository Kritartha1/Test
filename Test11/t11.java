// Test 11: 7:30-9:00pm
// 1: https://leetcode.com/problems/ones-and-zeroes/   
class Solution {
    Integer[][][] dp;
    public int findMaxForm(String[] strs, int m, int n) {
        
        int ans=0;
        int N=strs.length;
        dp=new Integer[N][101][101];
        int[][] count=new int[N][2];
        for(int i=0;i<N;i++){
            for(int j=0;j<strs[i].length();j++){
                int temp=(int)(strs[i].charAt(j)-'0');
                if(temp==0){
                    count[i][0]++;
                }else{
                    count[i][1]++;
                }
            }
        }
        return helper(count,m,n,0,0,0);
        
    }
    
    public int helper(int[][] count,int m,int n,int currM,int currN,int i){
        if(i>=count.length){
            return 0;
        }
        if(currM>m||currN>n){
            return 0;
        }
        if(currM==m&&currN==n){
            
            return 0;
        }
        if(dp[i][currM][currN]!=null){
            return dp[i][currM][currN];
        }
        int ans=0;
        int b=helper(count,m,n,currM,currN,i+1);
        if(currM+count[i][0]<=m&&currN+count[i][1]<=n){
            int a=helper(count,m,n,currM+count[i][0],currN+count[i][1],i+1);
            
            
            
            ans=Math.max(1+a,b);
        }
        else{
            ans=b;
        }
        return dp[i][currM][currN]=ans;
    }
}
// 2: https://leetcode.com/problems/maximum-length-of-repeated-subarray/
class Solution {
    Integer[][] dp;
    int res=0;
    public int findLength(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        dp=new Integer[m+1][n+1];
        solve(nums1,nums2,m,n);
        return res;
    }
    int solve(int[] nums1,int[] nums2,int m,int n){
        if(m<=0||n<=0){
            return 0;
        }
        if(dp[m][n]!=null){
            return dp[m][n];
        }
        
        
        int ans=0;
        
        if(nums1[m-1]==nums2[n-1]){
            ans=1+solve(nums1,nums2,m-1,n-1);
            //if elements at m th and nth index are equal then look for m-1 and n-1 so that it fulfills a subarray..
           
        }
        //else if nums1[m-1]!=nums2[n-1]-->means a subarray cannot be formed for that index..
        //but for m,n-1 and m-1,n there can be subarray answer..
        //so call those methods...
        
        //and if  nums1[m-1]!=nums2[n-1]...ans=0.
        
        
        solve(nums1,nums2,m,n-1);//res will take care of the answers we got from these calls
        solve(nums1,nums2,m-1,n);
           
        
        dp[m][n]=ans;
        res=Math.max(dp[m][n],res);
        return dp[m][n];
    }
}
// 3: https://leetcode.com/problems/reorganize-string/
class Solution {
    public String reorganizeString(String s) {
        int n=s.length();
        int[] mp=new int[26];
        int max=0;
        int c=s.charAt(0);
        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            mp[ch-'a']++;
            if(mp[ch-'a']>max){
                max=mp[ch-'a'];
                c=ch;
            }
        }
        
        if(max>((n+1)/2)){//i.e max>n-max+1
            return "";
        }
        
        char[] ch=new char[n];
        int i=0;
        for(;i<n&&mp[c-'a']>0;i+=2){
            
            ch[i]=(char)c;
            
            
            mp[c-'a']--;
            
        }
        
        for(int j=0;j<26;j++){
            if(mp[j]>0){
                while(mp[j]>0){
                   if(i>=n){
                       i=1;
                   }
                    ch[i]=(char)(j+'a');
                     
                    mp[j]--;
                    i+=2;
                }
            }
        }
        return String.valueOf(ch);
        
    }
}
// 4: https://leetcode.com/problems/reverse-nodes-in-k-group/

