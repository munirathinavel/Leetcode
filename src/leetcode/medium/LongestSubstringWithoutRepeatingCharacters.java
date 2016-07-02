package leetcode.medium;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * http://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters.

 Examples:

 Given "abcabcbb", the answer is "abc", which the length is 3.

 Given "bbbbb", the answer is "b", with the length of 1.

 ------------------------------------------------------------------------------------------------------------------------------------------------
 Approach: (V.v.v. tricky : Understand this logic and write code)
    1. Lookup if a character exist in substring instantaneously using array to store characters that have appeared
    2. If previously character is not part of Substring (not found before) => update its position and increment length
    3. If it was part of Substring (found before), then our substring changes. It becomes substring
        starting from next character of previous instance to currently scanned character.
    4. Keep track of max length everytime and update if its greater.

 */
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters obj = new LongestSubstringWithoutRepeatingCharacters();

        String input = "abcabceg";
        int length = obj.lengthOfLongestSubstring(input);
        System.out.println(length);
    }

    public int lengthOfLongestSubstring(String s){
        int maxLen = 0;
        int[] visited = new int[256];           //stores the position of characters visited. Initialize it with -1
        Arrays.fill(visited, -1);

        int prev = 0;                           //to keep track of prev location of character
        for(int i = 0; i < s.length(); i++){

            if(visited[s.charAt(i)] >= prev){         //If character was previously visited, then substring changes.
                prev = visited[s.charAt(i)] + 1;    //Get prev to previous position + 1. As new substring starts from "next char of previous instance of current char"
            }

            visited[s.charAt(i)] = i;       //update current position
            maxLen = Math.max(i - prev + 1, maxLen);
        }

        //retrieve longest substring
        StringBuilder sb = new StringBuilder();
        int temp = maxLen;

        while(temp > 0){
            int value = 0, index = 0;
            //get max value and its index from visited. Update it to -1 so we don't get same index again
            for(int i = 0; i < visited.length; i++){
                if(visited[i] != -1 && visited[i] > value){
                    value = visited[i];
                    index = i;
                }
            }
            visited[index] = -1;
            sb.append((char) index);

            temp--;
        }
        System.out.println(sb.reverse().toString());

        return maxLen;
    }

}