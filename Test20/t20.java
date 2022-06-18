// Test 20:
// 1: https://www.geeksforgeeks.org/weighted-job-scheduling/ 

// 2: https://leetcode.com/problems/palindrome-partitioning/    

//Approach 1: time O(N*2^N)   recursive stack :space(N)
//make recursive tree and everything will be clear...

class Solution {
    
    public List<List<String>> partition(String s) {
        List<List<String>> ans=new ArrayList<>();
        helper(ans,new ArrayList<>(),s,0);
        return ans;  
        
    }
    
    boolean isPalindrome(String s,int i,int j){
        if(i>j){
            return true;
        }
        
        return s.charAt(i)==s.charAt(j-1)&&isPalindrome(s,i+1,j-1);
    }
    
    void helper(List<List<String>> ans,List<String> path,String s,int i){
        if(i>=s.length()){
            List<String> temp=new ArrayList<String>();
            temp.addAll(path);
            ans.add(temp);
            return ;
            
        }
        for(int j=i+1;j<=s.length();j++){
            if(isPalindrome(s,i,j)){
                path.add(s.substring(i,j));
                helper(ans,path,s,j);
                path.remove(path.size()-1);
            }
        }
        
    }
    
}


//Approach 2: faster---> time O(N*2^N)   2d array+recursive stack :space(N*N+ N)=space(N*N)
//we are eliminating one additional iteration to check if substring is a palindrome or not.

class Solution {
    
    //make recursive tree and everything will be clear...
    
    public List<List<String>> partition(String s) {
        List<List<String>> ans=new ArrayList<>();
        int n=s.length();
        boolean[][] dp=new boolean[n][n];
        helper(ans,new ArrayList<>(),s,0,dp);
        return ans;
        
        
    }
    
    
    
    void helper(List<List<String>> ans,List<String> path,String s,int i,boolean[][] dp){
        if(i>=s.length()){
            List<String> temp=new ArrayList<String>();
            temp.addAll(path);
            ans.add(temp);
            return ;
            
        }
        for(int j=i;j<s.length();j++){
            if(s.charAt(i)==s.charAt(j)&&((j-i)<=2||dp[i+1][j-1])){
                dp[i][j]=true;
                path.add(s.substring(i,j+1));
                helper(ans,path,s,j+1,dp);
                path.remove(path.size()-1);
            }
        }
        
    }
    
    
}

// 3: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/   

//only binary search time complexity: O(d)
//d= diff initial r and  l.
//d=matrix[m-1][n-1]-matrix[0][0];
//count() will return number of elements having value <=val
//count : time complexity : o(m+n)
        
//binary search with count method is O((m+n)*log(d))

//Approach 1:
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int m=matrix.length;
        int n=matrix[0].length;
        int l=matrix[0][0];
        int r=matrix[m-1][n-1];
        int ans=-1;
        //only binary search time complexity: O(d)
        //d= diff initial r and  l.
        //d=matrix[m-1][n-1]-matrix[0][0];
        
        //binary search with count method is O((m+n)*log(d))
        while(l<=r){
            int mid=l+(r-l)/2;
            //count() returns number of elements having value <=val
            //if count returns value >=k...means we can get an answer at mid or before mid...
            //not certain about mid bcoz l+(r-l)/2 might not be an element from the matrix..
            if(count(matrix,mid)>=k){
                ans=mid;
                r=mid-1;
                
            }else{
                l=mid+1;
            }
        }
        return ans;
        
    }
    
    //count() will return number of elements having value <=val
    //count : time complexity : o(m+n)
    int count(int[][] matrix,int val){
        int r=0;
        int cnt=0;
        int c=matrix.length-1;
        for(;r<matrix.length&&c>=0;r++){
            while(c>=0&&matrix[r][c]>val){
                --c;
            }
            cnt+=(c+1);
            
        }
        return cnt;
    }
}


//Approach 2:  time O(klog(k)) and space O(k)
class Solution { // 18 ms, faster than 32.44%
    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, ans = -1; // For general, the matrix need not be a square
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int r = 0; r < Math.min(m, k); ++r)
            minHeap.offer(new int[]{matrix[r][0], r, 0});

        for (int i = 1; i <= k; ++i) {
            int[] top = minHeap.poll();
            int r = top[1], c = top[2];
            ans = top[0];
            if (c + 1 < n) minHeap.offer(new int[]{matrix[r][c + 1], r, c + 1});
        }
        return ans;
    }
}

// 4: https://leetcode.com/problems/range-sum-query-immutable/   
class NumArray {
    
    int[] prefixSum;
    public NumArray(int[] nums) {
        
        this.prefixSum=new int[nums.length+1];
        for(int i=1;i<=nums.length;i++){
            this.prefixSum[i]=nums[i-1]+this.prefixSum[i-1];
        }
    }
    
    public int sumRange(int left, int right) {
        return prefixSum[right+1]-prefixSum[left];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */
