// Test 6: 11:30-1:30+(To do: more questions on priority queue+math)
// 1: https://leetcode.com/problems/excel-sheet-column-title/ 
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder sb=new StringBuilder();
        int curr=0;
        
        while(columnNumber!=0){
            curr=(--columnNumber)%26;
             System.out.println(curr);
            sb.append((char)('A'+curr));
            
            
            columnNumber/=26;
            
            
            
        }
        return sb.reverse().toString();
    }
}
// 2: https://leetcode.com/problems/design-add-and-search-words-data-structure/
// Time Complexity:
// addWord() - O(n), n = length of the new word
// search() - Worst case: O(m), m = the total number of characters in the Trie
class WordDictionary {
    
    //This data structure is called trie
    boolean isEndOfWord;
    WordDictionary[] children;
    public WordDictionary() {
        this.isEndOfWord=false;
        this.children=new WordDictionary[26];
    }
    
    public void addWord(String word) {
        WordDictionary curr=this;
        for(char c:word.toCharArray()){
            if(curr.children[c-'a']==null){
                curr.children[c-'a']=new WordDictionary();
            }
            curr=curr.children[c-'a'];
        }
        curr.isEndOfWord=true;
    }
    
    public boolean search(String word) {
        WordDictionary curr=this;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(c=='.'){
                for(WordDictionary child:curr.children){
                    if(child!=null&&child.search(word.substring(i+1))) return true;
                }
                return false;
            }
            if(curr.children[c-'a']==null)return false;
            curr=curr.children[c-'a'];
        }
        
        return curr!=null&&curr.isEndOfWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
// 3: https://practice.geeksforgeeks.org/problems/largest-subarray-with-0-sum/1/
class GfG
{
    int maxLen(int arr[], int n)
    {
        // Your code here
        int maxLength=0;
        int sum=0;
        HashMap<Integer,Integer> mp=new HashMap<>();
        for(int i=0;i<n;++i){
            sum+=arr[i];
            if(sum==0)maxLength=1+i;
            if(mp.containsKey(sum)){
                maxLength=Math.max(maxLength,i-mp.get(sum));
            }
            else{
                mp.put(sum,i);
            }
            
        }
        return maxLength;
    }
}
// 4: https://leetcode.com/problems/minimum-cost-to-merge-stones/     
