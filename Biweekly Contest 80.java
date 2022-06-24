//leetcode.com/contest/biweekly-contest-80/

//Strong Password Checker II3     https://leetcode.com/problems/strong-password-checker-ii/
class Solution {
    
    public boolean strongPasswordCheckerII(String password) {
        boolean digit=false;
        boolean lowerCase=false;
        boolean upperCase=false;
        boolean special=false;
        char[] specials=("!@#$%^&*()-+").toCharArray();
        HashSet<Character> s=new HashSet<>();
        for(char c:specials){
            s.add(c);
        }
        
        
        char initial='/';
        
        int n=password.length();
        
        for(int i=0;i<n;i++){
            char c=password.charAt(i);
            
            lowerCase=Character.isLowerCase(c)|lowerCase;
            upperCase=Character.isUpperCase(c)|upperCase;
            digit=Character.isDigit(c)|digit;
            special=s.contains(c)|special;
            
            if(c==initial){
                return false;
            }
            initial=c;
            
            
        }
        // System.out.println(String.format("lowerCase %s,upperCase %s, digit %s specia %s", lowerCase,upperCase,digit,special ));
        return (n>=8)&&lowerCase&&upperCase&&digit&&special;
        
        
        
    }
}

//Successful Pairs of Spells and Potions4   https://leetcode.com/problems/successful-pairs-of-spells-and-potions/

//Approach 1:
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        
        Arrays.sort(potions);
        
        int n=potions.length;
        TreeMap<Long,Integer> mp=new TreeMap<>();
        //returns key=required potions and value = index of that potion.
        mp.put(Long.MAX_VALUE,n);
        
        for(int i=0;i<potions.length;i++){
            mp.putIfAbsent((long)potions[i],i);//puting the lowest index of duplicate values
        }
        
        for(int i=0;i<spells.length;i++){
            long need=(success+spells[i]-1)/spells[i];
            int r=mp.ceilingEntry(need).getValue();
            //mp.ceilingEntry(key):Returns a key-value mapping associated with the least key greater than or equal to the given key, or null if there is no such key.
            
            spells[i]=(n-r);//that is discarding all the elements from 0 to r-1.
        }
        return spells;
        
    }
}

//Approach 2:    
class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        
        int m=spells.length;
        int n=potions.length;
        
        int[] spell_copy=spells.clone();
        
        Arrays.sort(spells);
        Arrays.sort(potions);
        
        int j=n-1;
        
        HashMap<Integer,Integer> mp=new HashMap<>();
        
        for(int i=0;i<m;i++){
            //note: spells[i]*potions[j]*1l may cause int overflow.
            //bcoz first two parameters are integers.
            //se we multilply 1l at starting to avoid integer overflow.
            while(j>=0&&1l*spells[i]*potions[j]>=success){
                --j;
            }
           mp.put(spells[i],n-j-1);
            
        }
        
        
        for(int i=0;i<m;i++){
            spell_copy[i]=mp.get(spell_copy[i]);
        }
        return spell_copy;
        
        
    }
}
//Match Substring After Replacement6           https://leetcode.com/problems/match-substring-after-replacement/submissions/
class Solution {
    //leetcode.com/problems/match-substring-after-replacement/discuss/2138790/Greedy-Approach-or-O(N-*K)-or-C%2B%2B-Solution
    HashMap<Character,HashSet<Character>> mp;
    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        int n=s.length();
        int k=sub.length();
        mp=new HashMap<>();
        
        
        for(char[] pair:mappings){
            mp.putIfAbsent(pair[0],new HashSet<Character>());
            mp.get(pair[0]).add(pair[1]);
            
        }
        
        for(int i=0;i<=n-k;i++){
            if(helper(i,s,sub,k)){
                return true;
            }
        }
        return false;
        
    }
    boolean helper(int i,String s,String sub,int k){
        for(int j=i;j<i+k;j++){
             char a=s.charAt(j);
             char b=sub.charAt(j-i);
           
            if((a!=b)&&!(mp.getOrDefault(b,new HashSet<>()).contains(a))){
                return false;
            }
            
            
        }
        return true;
    }
}


//Count Subarrays With Score Less Than K          https://leetcode.com/problems/subarray-product-less-than-k/

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        //Sliding window: O(n)--time. O(1) space.
        if(k==0)return 0;
        int n=nums.length,ans=0,i=0,j=0,prod=1;
        while(j<n){
            prod*=nums[j];
            while(j>=i&&prod>=k){
                prod/=nums[i++];
                
            }
            ans+=j-i+1;
            j++;
            
        }
        return ans;
    }
}
