/*
Given an unsorted array arr[] of size n, containing elements from the range 1 to n, it is known that one number in this range is missing, and another number occurs twice in the array, find both the duplicate number and the missing number.

Examples:

Input: arr[] = [2, 2]
Output: [2, 1]
Explanation: Repeating number is 2 and the missing number is 1.
Input: arr[] = [1, 3, 3] 
Output: [3, 2]
Explanation: Repeating number is 3 and the missing number is 2.
Input: arr[] = [4, 3, 6, 2, 1, 1]
Output: [1, 5]
Explanation: Repeating number is 1 and the missing number is 5.
Constraints:
2 ≤ n ≤ 10^6
1 ≤ arr[i] ≤ n
*/

class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        int n=arr.length;
        int xor=0;
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<n;i++){
            int index=Math.abs(arr[i])-1;
            if(arr[index]<0){
                ans.add(Math.abs(arr[i]));
            }else{
                arr[index]=-arr[index];
            }
           xor^=Math.abs(arr[i]);
           xor^=(i+1);
        }
        xor^=ans.get(0);
        ans.add(xor);
        return ans;
    }
}
