Test 3

//1: https://leetcode.com/problems/maximum-product-of-three-numbers/

//Approach 1: Time O(nlogn). Space: O(logn)
class Solution {
    //
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        return Math.max(nums[0]*nums[1]*nums[n-1],nums[n-1]*nums[n-2]*nums[n-3]);
    }
}
//Approach 2:Time O(n), Space O(1)
class Solution {
         
        public int maximumProduct(int[] nums) {
            
            int max1=Integer.MIN_VALUE,max2=Integer.MIN_VALUE,max3=Integer.MIN_VALUE;
            int min1=Integer.MAX_VALUE,min2=Integer.MAX_VALUE;
            
            for (int n:nums) {
                if (n>max1) {
                    max3=max2;
                    max2=max1;
                    max1=n;
                } else if(n>max2) {
                    max3=max2;
                    max2=n;
                } else if(n>max3) {
                    max3=n;
                }

                if (n<min1) {
                    min2=min1;
                    min1=n;
                } else if(n<min2) {
                    min2=n;
                }
            }
        return Math.max(max1*max2*max3, max1*min1*min2);
    }
}

//2: https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
// d is the number of characters in the input alphabet
    public final static int d = 256;
     
    /* pat -> pattern
        txt -> text
        q -> A prime number
    */
    static void search(String pat, String txt, int q)
    {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;
     
        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;
     
        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++)
        {
            p = (d*p + pat.charAt(i))%q;
            t = (d*t + txt.charAt(i))%q;
        }
     
        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++)
        {
            if ( p == t )
            {
                for (j = 0; j < M; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
            if ( i < N-M )
            {
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
     
                if (t < 0)
                t = (t + q);
            }
        }
    }
     
    /* Driver Code */
    public static void main(String[] args)
    {
        String txt = "GEEKS FOR GEEKS";
        String pat = "GEEK";
           
          // A prime number
        int q = 101;
       
          // Function Call
        search(pat, txt, q);
    }



//3: https://leetcode.com/problems/longest-common-prefix/

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n=strs.length;
        
        Arrays.sort(strs,(a,b)->Integer.compare(a.length(),b.length()));
        String s=strs[0];
        int m=s.length();
        for(int i=0;i<n-1&&m>0;i++){
            int j=i+1;
            int k=0;
            for(;k<m&&s.charAt(k)==strs[j].charAt(k);k++){
                //
            }
            if(k<m){
                s=s.substring(0,k);
            }
            m=s.length();
            
            
        }
        return s;
        
    }
}

//4: https://practice.geeksforgeeks.org/problems/distance-of-nearest-cell-having-1-1587115620/1

class Solution {
    public String longestCommonPrefix(String[] strs) {
        int n=strs.length;
        
        Arrays.sort(strs,(a,b)->Integer.compare(a.length(),b.length()));
        String s=strs[0];
        int m=s.length();
        for(int i=0;i<n-1&&m>0;i++){
            int j=i+1;
            int k=0;
            for(;k<m&&s.charAt(k)==strs[j].charAt(k);k++){
                //
            }
            if(k<m){
                s=s.substring(0,k);
            }
            m=s.length();
            
            
        }
        return s;
        
    }
}

