
// Test 8:
// 1: https://leetcode.com/problems/maximum-number-of-visible-points/ 
class Solution {
    //https://leetcode.com/problems/maximum-number-of-visible-points/discuss/877845/JAVA-Sliding-Window
    
    //Sliding window
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        List<Double> angles=new ArrayList<>();
        
        int x=location.get(0);
        int y=location.get(1);
        
        int dx=0,dy=0;
        int count=0;
        
        for(List<Integer> pair:points){
            dx=pair.get(0)-x;
            dy=pair.get(1)-y;
            if(dx==0&&dy==0){
                count++;
                continue;
                
            }
            angles.add(Math.atan2(dx,dy)*180/Math.PI);
        }
        
        Collections.sort(angles);
        int size=angles.size();
        
        
        //Explanation for adding 360
        //https://leetcode.com/problems/maximum-number-of-visible-points/discuss/877845/JAVA-Sliding-Window/720209
        for(int i=0;i<size;i++){
            angles.add((angles.get(i)+360));
        }
        
        size=angles.size();
        int ans=count;
        int i=0;
        
        for(int j=0;j<size;j++){
            while(angles.get(j)-angles.get(i)>angle){
                ++i;
            }
            ans=Math.max(ans,j-i+1+count);
        }
        return ans;
        
    }
}
// 2: https://practice.geeksforgeeks.org/problems/mobile-numeric-keypad5456/1 
class Solution
{
    int[][] l={{0,8},{1,2,4},{2,1,3,5},{3,2,6},{4,1,5,7},{5,2,4,6,8},{6,3,5,9},{7,4,8},{8,5,7,9,0},{9,6,8}};
		
   
    Long[][] dp;
    
	public long getCount(int N)
	{
		// Your code goes here
		dp=new Long[10][N+1];
		long ans=0;
		for(int i=0;i<10;i++){
		    ans+=solve(i,N-1);
		}
		return ans;
	}
	long solve(int i,int N){
	    if(N==0)return 1;
    	if(dp[i][N]!=null)return dp[i][N];
    	long ans=0;
    	for(int neighbours:l[i]){
        	ans+=solve(neighbours,N-1);
        }
        
	    
    return dp[i][N]=ans;
	}

}
// 3: https://practice.geeksforgeeks.org/problems/k-anagrams-1/0
class Solution {
    public List<List<String>> Anagrams(String[] string_list) {
        // Code here
        HashMap<String,ArrayList<String>> mp=new HashMap<>();
        List<List<String>> ans=new ArrayList<>();
        
        int n=string_list.length;
        for(int i=0;i<n;i++){
            char[] a=string_list[i].toCharArray();
            Arrays.sort(a);
            String s=String.valueOf(a);
            ArrayList<String> A=mp.getOrDefault(s,new ArrayList<String>());
            A.add(string_list[i]);
            mp.put(s,A);
            
        }
        for(Map.Entry m:mp.entrySet()){
            ans.add((List<String>)m.getValue());
        }
        return ans;
    }
}


// 4: https://practice.geeksforgeeks.org/problems/smallest-positive-missing-number-1587115621/1/
class Solution
{
    //Function to find the smallest positive number missing from the array.
    static int missingNumber(int arr[], int size)
    {
        
        PriorityQueue<Integer> q=new PriorityQueue<>();
        int min=1;
        
        for(int i=0;i<size;i++){
            if(arr[i]>0)q.add(arr[i]);
        }
        while(!q.isEmpty()){
            if(q.peek()!=min)return min;
            while(!q.isEmpty()&&q.peek()==min){
                q.poll();
            }
            ++min;
            
        }
        return min;
        
        
    }
}
