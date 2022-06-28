//leetcode.com/problems/range-sum-query-mutable/
//Explanation:https://www.geeksforgeeks.org/segment-tree-set-1-sum-of-given-range/

class NumArray {
    int[] seg;
    int[] nums;
    int n;
    int N;
    public NumArray(int[] nums) {
        this.nums=nums;
        this.n=nums.length;
        int x=(int)Math.ceil(Math.log(n)/Math.log(2));
        this.N=2*((int)Math.pow(2,x))-1;
        this.seg=new int[this.N];
        constructST(nums,0,n-1,0);
        
        
    }
    int constructST(int[] nums,int l,int r,int i){
        if(l==r){
            return seg[i]=nums[l];
            
        }
        int mid=l+(r-l)/2;
        return seg[i]=constructST(nums,l,mid,2*i+1)+constructST(nums,mid+1,r,2*i+2);
        
        
    }
    
    public void update(int index, int val) {
        int diff=val-nums[index];
        nums[index]=val;
        update(diff,0,n-1,index,0);
        
    }
    void update(int diff,int l,int r,int idx,int i){
        if(idx<l||idx>r){
            return ;
        }
        seg[i]+=diff;
        if(l!=r){
            int mid=l+(r-l)/2;
            update(diff,l,mid,idx,2*i+1);
            update(diff,mid+1,r,idx,2*i+2);
        }
    }
    
    public int sumRange(int left, int right) {
        return sumRange(left,right,0,n-1,0);
    }
    int sumRange(int left,int right,int l,int r,int i){
        if(right<l||r<left){
            return 0;
        }
        if(left<=l&&right>=r){
            return seg[i];
        }
        int mid=l+(r-l)/2;
        return sumRange(left,right,l,mid,2*i+1)+sumRange(left,right,mid+1,r,2*i+2);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
