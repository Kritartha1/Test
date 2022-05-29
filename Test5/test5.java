//{<Test 5/>}: 

//1: https://leetcode.com/problems/valid-palindrome-ii/

class Solution {
    public boolean validPalindrome(String s) {
        int i=0;
        int n=s.length();
        int j=n-1;
        boolean pal=true;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                
                pal=isValidPalindrome(s,i+1,j)||isValidPalindrome(s,i,j-1);
                break;
            }
            i++;
            --j;
        }
        return pal;
    }
    public boolean isValidPalindrome(String s,int i,int j){
        if(i<j){
           return s.charAt(i)==s.charAt(j)&&isValidPalindrome(s,i+1,j-1);
        }
        return true;
    }
}

//2: https://www.geeksforgeeks.org/implement-two-stacks-in-an-array/

// * Structure of the class is
// class TwoStack
// {

// 	int size;
// 	int top1,top2;
// 	int arr[] = new int[100];

// 	TwoStack()
// 	{
// 		size = 100;
// 		top1 = -1;
// 		top2 = size;
// 	}
// }*/

class Stacks
{
    
    
    //Function to push an integer into the stack1.
    void push1(int x, TwoStack sq)
    {
        sq.top1+=1;
        sq.arr[sq.top1]=x;
        
    }

    //Function to push an integer into the stack2.
    void push2(int x, TwoStack sq)
    {
        sq.top2-=1;
        sq.arr[sq.top2]=x;
    }

    //Function to remove an element from top of the stack1.
    int pop1(TwoStack sq)
    {
        int a=sq.top1;
        if(a==-1){
            return -1;
        }
        sq.top1-=1;
        return sq.arr[a];
    }

    //Function to remove an element from top of the stack2.
    int pop2(TwoStack sq)
    {
        int a=sq.top2;
        if(a==sq.size){
            return -1;
        }
        sq.top2+=1;
        return sq.arr[a];
    }
}




//3: https://leetcode.com/problems/number-of-operations-to-make-network-connected/

//Approach 1:
class Solution {
   
    public int makeConnected(int n, int[][] connections) {
        HashMap<Integer,ArrayList<Integer>> graph =new HashMap<>();
        int count=connections.length;
        //i.e cables =count...
        //then cables count >=number of nodes-1.
        int connectedComponents=0;
        boolean[] vis=new boolean[n];
        buildGraph(graph,connections);
        if(count>=n-1){
            //ans=no. of connected components...
            for(int i=0;i<n;i++){
                if(!vis[i]){
                    dfs(i,graph,vis);
                    ++connectedComponents;
                }
            }
            
            return connectedComponents-1;
        }
        return -1;
        
    }
    public void dfs(int i,HashMap<Integer,ArrayList<Integer>> graph,boolean[] vis){
        vis[i]=true;
        for(int neighbour:graph.getOrDefault(i,new ArrayList<Integer>())){
            if(!vis[neighbour]){
                dfs(neighbour,graph,vis);
            }
        }
    }
    public void buildGraph(HashMap<Integer,ArrayList<Integer>> graph,int[][] connections){
        
        for(int connection[]:connections){
            ArrayList<Integer> temp=graph.getOrDefault(connection[0],new ArrayList<Integer>());
            ArrayList<Integer> temp2=graph.getOrDefault(connection[1],new ArrayList<Integer>());
            temp.add(connection[1]);
            temp2.add(connection[0]);
            graph.put(connection[0],temp);
            graph.put(connection[1],temp2);
            
            
        }
        
    }
}

//Approach 2: Union find...faster 

class Solution {
    class Node{
        int parent;
        int rank;
        Node(int parent,int rank){
            this.parent=parent;
            this.rank=rank;
        }
    }
    public int makeConnected(int n, int[][] connections) {
        int m=connections.length;
        if(m<n-1)return -1;
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++){
            nodes[i]=new Node(-1,0);
        }
        int components=n;
        int ans=Components(nodes,connections,n,components);
        return ans-1;
    }
    public int Components(Node[] nodes,int[][] connections,int n,int components){
        for(int[] edge:connections){
            int from=find(edge[0],nodes);
            int to=find(edge[1],nodes);
            if(from!=to){
                union(from,to,nodes);
                components--;
            }
        }
        return components;
    }
    public int find(int v,Node[] nodes){
        if(nodes[v].parent==-1){
            return v;
        }
        return nodes[v].parent=find(nodes[v].parent,nodes);
    }
    public void union(int from,int to,Node[] node){
        if(node[from].rank<node[to].rank){
            node[from].parent=to;
        }
        else if(node[from].rank>node[to].rank){
            node[to].parent=from;
        }
        else{
            node[to].rank+=1;
            node[from].parent=to;
        }
    }
}


//4: https://leetcode.com/problems/ugly-number-ii/

class Solution {
    public int nthUglyNumber(int n) {
        int ugly[]=new int[n];
        ugly[0]=1;
        int twos_next=2,i2=0;
        int threes_next=3,i3=0;
        int fives_next=5,i5=0;
        for(int i=1;i<n;i++){
            ugly[i]=Math.min(Math.min(threes_next,fives_next),twos_next);
            if(ugly[i]==twos_next){
                ++i2;
                twos_next=ugly[i2]*2;
            }
            if(ugly[i]==threes_next){
                ++i3;
                threes_next=ugly[i3]*3;
            }
            if(ugly[i]==fives_next){
                ++i5;
                fives_next=ugly[i5]*5;
            }
        }
        return ugly[n-1];
        
    }
}


