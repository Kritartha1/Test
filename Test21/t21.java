// Test 21:


// 1: https://leetcode.com/problems/basic-calculator-ii/   



//Approach 1: stack slower....
class Solution {
    public int calculate(String s) {
        
        //Operator precedence ...+ and - will have same operator precedence and * and / will have same preference..
        //precedence(* or / ) > precedence(+ or -)
        
        if(s==null||s.isEmpty()){
            return 0;
        }
        int n=s.length();
        Deque<Integer> st=new ArrayDeque<>();
        int currNum=0;
        char sign='+';
        for(int i=0;i<n;i++){
            char curr=s.charAt(i);
            if(Character.isDigit(curr)){
                currNum=(currNum*10)+(curr-'0');
                
            }
            if(!Character.isDigit(curr)&&!Character.isWhitespace(curr)||i==n-1){
                if(sign=='+'){
                    st.push(currNum);
                }else if(sign=='-'){
                    st.push(-currNum);
                }
                else if(sign=='*'){
                    st.push(st.pop()*currNum);
                }
                else if(sign=='/'){
                    st.push(st.pop()/currNum);
                }
                currNum=0;
                sign=curr;
            }
        }
        
        int ans=0;
        while(!st.isEmpty()){
            ans+=st.pop();
        }
        return ans;
    }
}


//Approach 2:

//Faster: 1+2-3
//for  '+' , last num is 0 ...add 0 to ans..lastNum becomes 1. sign becomes='+'
//for  '-' , last num is 1 ...add 1 to ans..lastNum becomes 2. sign  becomes='-'
//for  i=n-1 , last num is 2 ...add 2 to ans..lastNum becomes -3. 
//finally ans=ans+last sum=(0+1+2)+(-3)=0

class Solution {
    public int calculate(String s) {
        
        //Operator precedence ...+ and - will have same operator precedence and * and / will have same preference..
        //precedence(* or / ) > precedence(+ or -)
        
        if(s==null||s.isEmpty()){
            return 0;
        }
        int n=s.length();
        int ans=0;
        
        int currNum=0;
        int lastNum=0;
        char sign='+';
        for(int i=0;i<n;i++){
            char curr=s.charAt(i);
            if(Character.isDigit(curr)){
                currNum=(currNum*10)+(curr-'0');
                
            }
            if(!Character.isDigit(curr)&&!Character.isWhitespace(curr)||i==n-1){
                if(sign=='+'){
                    ans+=lastNum;
                    lastNum=currNum;
                }else if(sign=='-'){
                    ans+=lastNum;
                    lastNum=-currNum;
                }
                else if(sign=='*'){
                    lastNum*=currNum;
                }
                else if(sign=='/'){
                   lastNum/=currNum;
                }
                currNum=0;
                sign=curr;
            }
        }
        
       
        
        return ans=ans+lastNum;
    }
}
// 2: https://leetcode.com/problems/the-kth-factor-of-n/   

class Solution {
    
    public int kthFactor(int n, int k) {
        for(int i=1;i<Math.sqrt(n);++i){
            if(n%i==0&&--k==0){
                return i;
            }
        }
        for(int i=(int)Math.sqrt(n);i>=1;--i){
            //actually we have to check n%(n/i)==0
            //bco if i is a factor ...n/i is a factor..
            //i*(n/i)=n..
            //if n/i divides n -->>then i also divides n..
            //so n%(n/i)=n%(i)
            if(n%i==0&&--k==0){
                return n/i;
            }
        }
        return -1;
        
    }
    
    
}
// 3: http://geeksforgeeks.org/the-celebrity-problem/   

//Approach 1:
class Solution
{ 
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
    	// code here 
    	
    	HashMap<Integer,Integer> c=new HashMap<>();
    	int temp=0;
    	for(int j=0;j<n;j++){
    	    temp=0;
    	    for(int i=0;i<n;i++){
    	        temp+=M[i][j];
    	    }
    	    c.put(j,temp);
    	}
    	
    	for(int i=0;i<n;i++){
    	    temp=0;
    	    for(int j=0;j<n;j++){
    	        temp+=M[i][j];
    	    }
    	    if(c.get(i)==n-1&&temp==0){
    	        return i;
    	    }
    	}
    	return -1;
    }
}
//Approach 2:
class Solution
{ 
    //Function to find if there is a celebrity in the party or not.
    int celebrity(int M[][], int n)
    {
    	// code here 
    	
    	
    	
    	for(int i=0;i<n;i++){
    	    int col_one=0;
    	    int row_zero=0;
    	    for(int j=0;j<n;j++){
    	        if(i!=j&&M[i][j]==0){
    	            row_zero++;
    	        }
    	        if(i!=j&&M[j][i]==1){
    	            col_one++;
    	        }
    	    }
    	    if(row_zero==n-1&&col_one==n-1){
    	        return i;
    	    }
    	}
    	
    	
    	return -1;
    }
}

//Approach 3:
class Solution
{ 
    //Function to find if there is a celebrity in the party or not.
    int findCelebrityAmong(int n,int[][] M){
        if(n==0){
            return -1;
        }
        int id=findCelebrityAmong(n-1,M);
        
        //no celebrities..or  when a does not know b and b does not know a..
        //so not a celebrity which is known by n-1 people.....
        if(id==-1){
            return n-1;
        }
        if(M[id][n-1]==1){
            return n-1;
        }
        if(M[n-1][id]==1){
            return id;
        }
        
        //no celebrity
        return -1;
    }
    int celebrity(int M[][], int n)
    {
        int id=findCelebrityAmong(n,M);
        if(id==-1){
            return id;//no celebrity
        }
        int a=0,b=0;
        
        //to check that is is known by everyone..
        for(int i=0;i<n;i++){
            if(i!=id){
                a+=M[id][i];//id knows i
                b+=M[i][id];//i knows id
            }
        }
        if(a==0&&b==n-1){
            return id;
        }
        
    	
    	return -1;
    }
    
}

//Approach 4:
class Solution
{ 
   
         
    
    int celebrity(int M[][], int n)
    {
        Deque<Integer> st=new ArrayDeque<>();
        for(int i=0;i<n;i++){
            st.push(i);
        }
        while(st.size()>1){
            int a=st.poll();
            int b=st.poll();
            if(M[a][b]==1){
                st.push(b);
            }
            else{
                st.push(a);
            }
        }
        
        int c=st.poll();
        for(int i=0;i<n;i++){
            if(i!=c&&(M[c][i]==1||M[i][c]==0)){
                return -1;
            }
        }
        
    	
    	return c;
    }
    
}


// Fastest...
class Solution
{ 
     
    int celebrity(int M[][], int n)
    {
        int i=0;
        int j=n-1;
        while(i<j){
            if(M[i][j]==1){
                ++i;//i can't be that stranger celebrity
            }else{
                --j;
            }
        }
        for(int others=0;others<n;others++){
            if(i!=others&&(M[i][others]==1||M[others][i]==0)){
                return -1;
            }
        }
        return i;
        
    }
    
}
