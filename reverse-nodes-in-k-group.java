//leetcode.com/problems/reverse-nodes-in-k-group/

//Iterative approach : O(1) space.

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null){return head;}
        
        ListNode prev=null;
        ListNode curr=head;
        ListNode next=null;
        
        ListNode top=new ListNode(-1);
        ListNode pointer=top;//it will do the work of joining k reverse list to the tail of each list.
        //here eg: 1-->2-->3
        //here head =1.
        //after reversing,, 3--2-->1
        //so head will become tail.
        //after each reversal, pointer will point to head ..i.e tail and pointer's next will be the original head of the next k nodes to be reversed
        
        while(curr!=null){
            int i=0;
            for(;curr!=null&&i<k;i++){
                next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
            }
             
            if(i<k){
               
                curr=prev;
                prev=null;
                next=null;
                while(curr!=null){
                    next=curr.next;
                    curr.next=prev;
                    prev=curr;
                    curr=next;
                }
            }
            pointer.next=prev;
            
            pointer=head;//making pointer to point to the tail of the currently reversed list.
            //so in next iteration, pointer's next will be the head of the new reversed k nodes.
            
            head=curr;
            prev=null;
            next=null;
            
        }
        
        
        
        return top.next;
        
        
    }
}

//Recursive approach: recursion stack ..so O(n/k) space.
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null||head.next==null){return head;}
        ListNode prev=null;
        ListNode curr=head;
        ListNode next=null;
        //ListNode top=new ListNode(-1);
        int i=0;
        for(;curr!=null&&i<k;i++){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        //System.out.println("prev:"+(prev!=null?prev.val:-1));
        if(i<k){
            curr=prev;
            prev=null;
            next=null;
            while(curr!=null){
                next=curr.next;
                curr.next=prev;
                prev=curr;
                curr=next;
            }
        }    
        else{
            head.next=reverseKGroup(curr,k);
        }
        
        return prev;
        
        
    }
}
