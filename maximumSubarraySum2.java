// Maximum subarray sum 2

// You are given an array arr[] of integers and two integers a and b, You have to find the maximum possible sum of a contiguous subarray whose length is at least a and at most b.

// Examples:

// Input: arr[] = [4, 5, -1, -2, 6], a = 2, b = 4
// Output: 9
// Explanation: The subarray [4, 5] has length 2 and sum 9, which is the maximum among all subarrays of length between 2 and 4.
// Input: arr[] = [-1, 3, -1, -2, 5, 3, -5, 2, 2], a = 3, b = 5
// Output: 8
// Explanation: The subarray [3, -1, -2, 5, 3] has length 5 and sum 8, which is the maximum among all subarrays of length between 3 and 5.
// Constraints:
// 1 ≤ arr.size() ≤ 10^5
// -10^5 ≤ arr[i] ≤ 10^5
// 1 ≤ a ≤ b ≤ arr.size()


class Solution {
    public int maxSubarrSum(int[] arr, int a, int b) {
        
       int n=arr.length;
       int[] pref=new int[n];
       pref[0]=arr[0];
       for(int i=1;i<n;i++){
          pref[i]=pref[i-1]+arr[i]; 
       }
       Deque<Integer> q=new LinkedList<>();
    
       q.offerLast(n-1);
       int index=n-2;
       int maxSum=Integer.MIN_VALUE;
       for(int i=n-a;i>=0;i--){
           while(!q.isEmpty()&&(q.peek()>=i+b))q.pollFirst();
           int max=pref[q.peekFirst()];
           int sum=max;
           if(i!=0){
               sum-=pref[i-1];
           }
           maxSum=Math.max(sum,maxSum);
           if(index>=0){
           while(!q.isEmpty()&&pref[q.peekLast()]<=pref[index])q.pollLast();
           q.offerLast(index--);
           }
       }
       return maxSum;
    }
}