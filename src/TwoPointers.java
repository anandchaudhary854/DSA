import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {


    //Problem Statement #
    //Given an array of sorted numbers and a target sum, find a pair in the array whose sum is equal to the given target.
    //
    //Write a function to return the indices of the two numbers (i.e. the pair) such that they add up to the given target.
    //
    //Example 1:
    //
    //Input: [1, 2, 3, 4, 6], target=6
    //Output: [1, 3]
    //Explanation: The numbers at index 1 and 3 add up to 6: 2+4=6
    //Example 2:
    //
    //Input: [2, 5, 9, 11], target=11
    //Output: [0, 2]
    //Explanation: The numbers at index 0 and 2 add up to 11: 2+9=11

    static int[] findTargetSum(int[] arr, int target){
        int st = 0, end = arr.length -1;
        while (st < end){
            int sum = arr[st] + arr[end];
            if(sum == target){
                return new int[]{st, end};
            }else if (sum > target){
                end--;
            }else{
                st++;
            }
        }
        return new int[]{-1, -1};
    }

    //Problem Statement #
    //Given an array of sorted numbers, remove all duplicates from it. You should not use any extra space; after removing the duplicates in-place return the new length of the array.
    //
    //Example 1:
    //
    //Input: [2, 3, 3, 3, 6, 9, 9]
    //Output: 4
    //Explanation: The first four elements after removing the duplicates will be [2, 3, 6, 9].
    //Example 2:
    //
    //Input: [2, 2, 2, 11]
    //Output: 2
    //Explanation: The first two elements after removing the duplicates will be [2, 11].

    static int findNumOfUniqueElements(int[] arr){
        int result = 0;
        if (arr.length > 0) result +=1;
        else return 0;
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] != arr[i-1]){
                arr[result] = arr[i];
                result +=1;
            }
        }
        System.out.println(Arrays.toString(arr));
        return result;
    }

    //Problem Statement # (easy)
    //Given a sorted array, create a new array containing squares of all the number of the input array in the sorted order.
    //
    //Example 1:
    //
    //Input: [-2, -1, 0, 2, 3]
    //Output: [0, 1, 4, 4, 9]
    //Example 2:
    //
    //Input: [-3, -1, 0, 1, 2]
    //Output: [0 1 1 4 9]
    // redo this implemented after seeing the solution.
    static int[] squareSortedArr(int[] arr){
        int [] result = new int[arr.length];
        int left = 0, right = arr.length -1, sqIndx = arr.length - 1;
        while (left < right){
            int lsquare = arr[left] * arr[left];
            int rSquare  = arr[right] * arr[right];
            if(lsquare > rSquare){
                result[sqIndx--] = lsquare;
                left++;
            }else{
                result[sqIndx--] = rSquare;
                right--;
            }
        }
        return result;
    }

    //Problem Statement # (medium)
    //Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
    //
    //Example 1:
    //
    //Input: [-3, 0, 1, 2, -1, 1, -2]
    //Output: [-3, 1, 2], [-2, 0, 2], [-2, 1, 1], [-1, 0, 1]
    //Explanation: There are four unique triplets whose sum is equal to zero.
    //Example 2:
    //
    //Input: [-5, 2, -1, -2, 3]
    //Output: [[-5, 2, 3], [-2, -1, 3]]
    //Explanation: There are two unique triplets whose sum is equal to zero.
    static List<List<Integer>> findTriplets(int[] arr){
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < arr.length -2; i++) {
            List<Integer> innerResult = new ArrayList<>();
            innerResult.add(arr[i]);
            for (int j = i; j < arr.length -1; j++) {
                if(arr[i] + arr[j] + arr[ j + 1] == 0){
                    innerResult.add(arr[j]);
                    innerResult.add(arr[j+1]);
                    result.add(innerResult);
                    break;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        System.out.println(Arrays.toString(findTargetSum(new int[]{1, 2, 3, 4, 6}, 6)));
//        System.out.println(findNumOfUniqueElements(new int[]{2, 3, 3, 3, 6, 9, 9}));
//        System.out.println(Arrays.toString(squareSortedArr(new int[]{-2, -1, 0, 2, 3})));
        System.out.println(findTriplets(new int[]{-3, 0, 1, 2, -1, 1, -2}));
    }


}