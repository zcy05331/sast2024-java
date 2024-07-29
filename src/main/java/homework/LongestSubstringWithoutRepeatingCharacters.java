package homework;

import java.util.Arrays;
import java.util.Scanner;

public class LongestSubstringWithoutRepeatingCharacters {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        // TODO begin
        for (int i = 0; i < n; i++)
            for (int j = i; j >= 0; j--) {
                boolean[] visited = new boolean[256];
                boolean hasDuplicate = false;
                for (int k = j; k <= i; k++) {
                    if (visited[s.charAt(k)]) {
                        hasDuplicate = true;
                        break;
                    }
                    visited[s.charAt(k)] = true;
                }
                if (!hasDuplicate)
                    ans = Math.max(ans, i - j + 1);
            }
        // TODO end
        return ans;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = input.nextLine();
        System.out.println(lengthOfLongestSubstring(s));
    }
}
