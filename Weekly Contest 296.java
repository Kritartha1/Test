//https://leetcode.com/contest/weekly-contest-296/

//1.https://leetcode.com/problems/min-max-game/

class Solution {
    public int minMaxGame(int[] nums) {
        int n=nums.length;
        
        while(n>1){
            int[] newNums=new int[n/2];
            for(int i=0;i<n/2;i++){
                int j=2*i;
                if((i&1)==0){//even
                    newNums[i]=Math.min(nums[j],nums[j+1]);
                }else{
                    newNums[i]=Math.max(nums[j],nums[j+1]);
                }
            }
            
            nums=newNums;
            n=nums.length;
        }
        return nums[0];
    }
}


//2.https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/

//Approach 1:
class SolutionX {
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        // int i=n-1;
        // int j=n-1;
        int ans=0;
//         while(j>=0){
//             while(j>0&&(nums[i]-nums[j-1]<=k)){
//                 --j;
//             }
            
//             //System.out.println("j:"+j+"and i:"+i);
           
//             ans+=1;
//             i=j-1;
//             j=i;
//         }
        
        int min=nums[0];
        int max=nums[0];
        for(int i=0;i<n;i++){
            max=Math.max(nums[i],max);
            min=Math.min(nums[i],min);
            if(max-min>k){
                ans++;
                max=nums[i];
                min=nums[i];
            }
        }
        return ans+1;
        //+1 to account for the last sequence where max-min<=k ending at pos i=n-1.
        //bcoz after that loop completes..so we have to add that sequence.
        
    }
    
}

//Alternative approach:
class Solution{
        public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        int i=n-1;
        int j=n-1;
        int ans=0;
        while(j>=0){
            while(j>0&&(nums[i]-nums[j-1]<=k)){
                --j;
            }
            
            //System.out.println("j:"+j+"and i:"+i);
           
            ans+=1;
            i=j-1;
            j=i;
        }
            return ans;
        }
}

//3.https://leetcode.com/problems/replace-elements-in-an-array/

class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            mp.put(nums[i],i);
        }
        for(int[] op:operations){
            nums[mp.get(op[0])]=op[1];
            mp.put(op[1],mp.get(op[0]));
        }
        return nums;
    }
}

//4.https://leetcode.com/problems/design-a-text-editor/

class TextEditor {
    StringBuilder sb;
    int pos;
    public TextEditor() {
        sb=new StringBuilder();
        pos=0;
    }
    
    public void addText(String text) {
        sb.insert(pos,text);
        pos+=text.length();
        //System.out.println("curr pos of cursor is :"+pos);
    }
    
    public int deleteText(int k) {
        int end=pos;
        pos=Math.max(pos-k,0);
        sb.delete(pos,end);
        //System.out.println("curr pos of cursor is :"+pos);
        return end-pos;
    }
    
    public String cursorLeft(int k) {
        pos=Math.max(pos-k,0);
        int start=Math.max(pos-10,0);
        //System.out.println("curr pos of cursor is :"+pos);
        return sb.substring(start,pos);
    }
    
    public String cursorRight(int k) {
        pos=Math.min(pos+k,sb.length());
        int start=Math.max(pos-10,0);
        //System.out.println("curr pos of cursor is :"+pos);
        return sb.substring(start,pos);
    }
}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */

