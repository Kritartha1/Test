// Test 13:


// 1: https://leetcode.com/problems/reverse-integer/  
class Solution {
    public int reverse(int x) {
        int I_MAX=Integer.MAX_VALUE;
        int I_MIN=Integer.MIN_VALUE;
        int a=0;
        while(x!=0){
            int r=x%10;
            x/=10;
            if(a>I_MAX/10||(a==I_MAX/10&&r>7)){
                return 0;
            }
            else if(a<I_MIN/10||(a==I_MIN/10&&r<-8)){
                return 0;
            }
            a=a*10+r;
        }
        return a;
    }
}


// 2: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/   

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode prev=dummy;
        ListNode curr=head;
        prev=dummy;
        
        while(curr!=null){
             ListNode temp=curr;
             int val=curr.val;
             int count=0;
             while(curr!=null&&curr.val==val){
                 curr=curr.next;
                 ++count;
             }
             if(count==1){
               prev=temp;

             }
              prev.next=curr;
         
         }
        
         return dummy.next;
    }
}




// 3: https://practice.geeksforgeeks.org/problems/circular-tour/1     
class Solution
{
    //Function to find starting point where the truck can start to get through
    //the complete circle without exhausting its petrol in between.
    int tour(int petrol[], int distance[])
    {
	// Your code here
	if(petrol.length!=distance.length){
	    return -1;
	}
	 int start=0;
	 int surplus=0;
	 int deficit=0;
	///if at a certain stage while travelling from petrol pump i to petrol pump i+1,
	//if excess petrol+petrol[i]<distance(i->i+1),then we can't start from that pterol pump or any other petrol pump before it..
	//so next possible starting point is may be (i+1)...
	//after iterating all the petrol pumps,if there is still some deficit after burning all the petrols,then there can't be any possible circular paths..
	
	 for(int i=0;i<petrol.length;i++){
	     surplus+=petrol[i]-distance[i];
	     if(surplus<0){
	         //means can't start from ith petrol pump or any other petrol pump before it.. 
	         start=i+1;
	         deficit+=surplus;
	         surplus=0;
	     }
	 }
	 return (surplus+deficit)>=0?start:-1;
    }
}
