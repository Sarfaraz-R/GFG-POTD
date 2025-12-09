/*
Array Duplicates
Difficulty: EasyAccuracy: 18.95%Submissions: 876K+Points: 2Average Time: 20m
Given an array arr[] of size n, containing elements from the range 1 to n, and each element appears at most twice, return an array of all the integers that appears twice.

Note: You can return the elements in any order but the driver code will print them in sorted order.

Examples:

Input: arr[] = [2, 3, 1, 2, 3]
Output: [2, 3] 
Explanation: 2 and 3 occur more than once in the given array.
Input: arr[] = [3, 1, 2] 
Output: []
Explanation: There is no repeating element in the array, so the output is empty.
Constraints:
1 ≤ n ≤ 10^6
1 ≤ arr[i] ≤ n
 */


class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> ans=new ArrayList<>();
        int n=arr.length;
        for(int i=0;i<n;i++){
           int ind=Math.abs(arr[i])-1;
           if(arr[ind]<0){
               ans.add(Math.abs(arr[i]));
           }else{
               arr[ind]=-arr[ind];
           }
        }
        return ans;
    }
}