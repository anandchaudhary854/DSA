import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class SlidingWindow {


    //Here, we are asked to find the average of all contiguous subarrays of size ‘5’ in the given array. Let’s solve this:
    // Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
    //Output: [2.2, 2.8, 2.4, 3.6, 2.8]
    static double[] findAverageOfContiguousSubArray(int [] arr, int k){
        double[] result = new double[arr.length - k +1];
        int st = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(i >= k - 1){
                result[st] = (double) sum/k;
                sum -= arr[st];
                st++;
            }
        }
        return result;
    }

    //Problem Statement # (easy)
    //Given an array of positive numbers and a positive number ‘k’, find the maximum sum of any contiguous subarray of size ‘k’.
    //
    //Example 1:
    //
    //Input: [2, 1, 5, 1, 3, 2], k=3
    //Output: 9
    //Explanation: Subarray with maximum sum is [5, 1, 3].

    static int findMaxSumContArr(int[] arr, int k){
        int result = 0;
        int st = 0, sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if(i >= k -1){
                result = Math.max(sum, result);
                sum -= arr[st];
                st++;
            }
        }
        return result;
    }

    //Problem Statement # (easy)
    //Given an array of positive numbers and a positive number ‘S’, find the length of the smallest contiguous subarray whose sum is greater than or equal to ‘S’. Return 0, if no such subarray exists.
    //
    //Example 1:
    //
    //Input: [2, 1, 5, 2, 3, 2], S=7
    //Output: 2
    //Explanation: The smallest subarray with a sum great than or equal to '7' is [5, 2].
    //Example 2:
    //
    //Input: [2, 1, 5, 2, 8], S=7
    //Output: 1
    //Explanation: The smallest subarray with a sum greater than or equal to '7' is [8].

    static int findSmallestSubArr(int[] arr, int sum){
        int result = Integer.MAX_VALUE;
        int st = 0, subSum =0;
        for (int i = 0; i < arr.length; i++) {
            subSum += arr[i];
            while(subSum >= sum){
                result = Math.min(result, i -st +1);
                subSum -= arr[st];
                st++;
            }
        }
        return result;
    }

    //Problem Statement # (medium)
    //Given a string, find the length of the longest substring in it with no more than K distinct characters.
    //
    //Example 1:
    //
    //Input: String="araaci", K=2
    //Output: 4
    //Explanation: The longest substring with no more than '2' distinct characters is "araa".
    //Example 2:
    //
    //Input: String="araaci", K=1
    //Output: 2
    //Explanation: The longest substring with no more than '1' distinct characters is "aa".

    static int findLongestSubString(String str, int k){
        int result = 0;
        int st = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
            if (map.size() > k){
                result = Math.max(result, i - st);
                map.put(str.charAt(st), map.get(str.charAt(st)) - 1);
                if(map.get(str.charAt(st)) == 0){
                    map.remove(str.charAt(st));
                }
                st++;
            }
        }
        return result;
    }

    //Problem Statement # (medium)
    //Given an array of characters where each character represents a fruit tree, you are given two baskets and your goal is to put maximum number of fruits in each basket. The only restriction is that each basket can have only one type of fruit.
    //
    //You can start with any tree, but once you have started you can’t skip a tree. You will pick one fruit from each tree until you cannot, i.e., you will stop when you have to pick from a third fruit type.
    //
    //Write a function to return the maximum number of fruits in both the baskets.
    //
    //Example 1:
    //
    //Input: Fruit=['A', 'B', 'C', 'A', 'C']
    //Output: 3
    //Explanation: We can put 2 'C' in one basket and one 'A' in the other from the subarray ['C', 'A', 'C']
    //Example 2:
    //
    //Input: Fruit=['A', 'B', 'C', 'B', 'B', 'C']
    //Output: 5
    //Explanation: We can put 3 'B' in one basket and two 'C' in the other basket.
    //This can be done if we start with the second letter: ['B', 'C', 'B', 'B', 'C']

    static int fruitsInBasket(Character[] arr){
        int result = 0;
        int st = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
            if(map.size() > 2){
                map.put(arr[st], map.get(arr[st]) - 1);
                if(map.get(arr[st]) == 0){
                    map.remove(arr[st]);
                }
                st++;
            }
            result = Math.max(result, i - st +  1);
        }
        return result;
    }

    //Problem Statement # (hard)
    //Given a string, find the length of the longest substring which has no repeating characters.
    //
    //Example 1:
    //
    //Input: String="aabccbb"
    //Output: 3
    //Explanation: The longest substring without any repeating characters is "abc".
    //Example 2:
    //
    //Input: String="abbbb"
    //Output: 2
    //Explanation: The longest substring without any repeating characters is "ab".

    static int findLongestSubStrWithRepeat(String str){
        int result = 0;
        int st = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            // increase or add frequency of  char at right end of the window
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
            //run the loop if right char frq becomes greater than 1
            while (map.get(str.charAt(i)) > 1){
                // decrease left end char frq
                map.put(str.charAt(st), map.get(str.charAt(st)) - 1);
                if(map.get(str.charAt(st)) == 0){
                    map.remove(str.charAt(st));
                }
                st++;
            }
            // keep updating the result for the max length of the window before it goes into while loop
            result = Math.max(result, i - st + 1);
        }
        return result;
    }

    //Problem Statement # (hard)
    //Given an array containing 0s and 1s, if you are allowed to replace no more than ‘k’ 0s with 1s,
    // find the length of the longest contiguous subarray having all 1s.
    //
    //Example 1:
    //
    //Input: Array=[0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1], k=2
    //Output: 6
    //Explanation: Replace the '0' at index 5 and 8 to have the longest contiguous subarray of 1s having length 6.
    //Example 2:
    //
    //Input: Array=[0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1], k=3
    //Output: 9
    //Explanation: Replace the '0' at index 6, 9, and 10 to have the longest contiguous subarray of 1s having length 9.

    static int longestSubWithKZeros(int[] arr, int k){
        int st = 0, result = 0, numOfZeros = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) numOfZeros++;
            while(numOfZeros > k){
                if(arr[st] == 0) numOfZeros--;
                st++;
            }
            result = Math.max(result, i -st + 1);
        }
        return result;
    }

    //Permutation in a String (hard) #
    //Given a string and a pattern, find out if the string contains any permutation of the pattern.
    //
    //Permutation is defined as the re-arranging of the characters of the string. For example, “abc” has the following six permutations:
    //
    //abc
    //acb
    //bac
    //bca
    //cab
    //cba
    //If a string has ‘n’ distinct characters it will have
    //n
    //!
    //n! permutations.
    //
    //Example 1:
    //
    //Input: String="oidbcaf", Pattern="abc"
    //Output: true
    //Explanation: The string contains "bca" which is a permutation of the given pattern.
    //Example 2:
    //
    //Input: String="odicf", Pattern="dc"
    //Output: false
    //Explanation: No permutation of the pattern is present in the given string as a substring.
    //Example 3:
    //
    //Input: String="bcdxabcdy", Pattern="bcdyabcdx"
    //Output: true
    //Explanation: Both the string and the pattern are a permutation of each other.



    public static void main(String[] args) {
//        System.out.println(Arrays.toString(findAverageOfContiguousSubArray(new int[]{1, 3, 2, 6, -1, 4, 1, 8, 2}, 5)));
//        System.out.println(findMaxSumContArr(new int[]{2, 1, 5, 1, 3, 2}, 3));
//        System.out.println(findSmallestSubArr(new int[]{2, 1, 5, 2, 8}, 7));
//        System.out.println(findLongestSubString("araaci", 1));
//        System.out.println(fruitsInBasket(new Character[]{'A', 'B', 'C', 'B', 'B', 'C'}));
//        System.out.println(findLongestSubStrWithRepeat("aabccbb"));
        System.out.println(longestSubWithKZeros(new int[]{0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1}, 2));
    }


}
