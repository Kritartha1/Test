https://www.codechef.com/LTIME109


/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t--!=0){
            int s=(sc.nextInt()-1)/10;
            int f=(sc.nextInt()-1)/10;
            System.out.println(Math.abs(s-f));
            
        }

       
	}
}





/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t--!=0){
            int A=sc.nextInt();
            int B=sc.nextInt();
            int n=sc.nextInt();
            int c=A^B;
            int q=find(c);
            int w=find(n);
            int max=(1<<((int)(Math.log(w & -w)/Math.log(2))+1))-1;
            // System.out.println("max:"+max);
            // if(c>max){
            //     System.out.println(-1);
            // }
            // else 
            if(c==0){
                System.out.println(0);
            }
            else if(c>=n&&n==w){
                System.out.println(-1);
            }
            else if((q<w)||(c<n)){
                    System.out.println(1);//correct...
            }
            else if(q==w){
                     System.out.println(2);//correct
            }
            else{
                    System.out.println(-1);
            }
            
            
            
        }

       
	}
	static int find(int a){
	    int r = a;
	
	  r = r | (r >> 1);
	  r = r | (r >> 2);
	  r = r | (r >> 4);
	  r = r | (r >> 8);
	  r = r | (r >> 16);
	
	  r = r ^ (r >> 1);
	  return r;
	}
}


import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		// your code goes here
		Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t--!=0){
            int n=sc.nextInt();
            int[] B=new int[n+1];
            int tot=0;
            for(int i=0;i<=n;i++){
                B[i]=sc.nextInt();
                tot^=B[i];
            }
            //logic: if n is odd, then xor of all the n+1 elements of B must be equal to imitial xor of all the n numbers
            //if n is even, then we can take any number as xor and derive others from it..
            
            //if xor(of n numbers)=tot.
            //then num[i]=xor^B[i]// (a^b^c)^(a^b)=c
            if(n%2==0){
                tot=B[0];
            }
            boolean found=false;
            for(int i=0;i<=n;++i){
                if(B[i]==tot&&!found){
                    found=true;
                }else{
                    System.out.print((B[i]^tot)+" ");
                }
            }
             System.out.println();
        }

       
	}
}
