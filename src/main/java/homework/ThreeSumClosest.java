package homework;

import java.util.Scanner;

public class ThreeSumClosest {
    public static int threeSumClosest(int[] nums, int target) {
        int n = nums.length, ans = 0;
        // TODO begin
        for (int i = 0; i < n; i++)
            for (int j = 0; j < i; j++)
                for (int k = 0; k < j; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < Math.abs(ans - target))
                        ans = sum;
                }
        // TODO end
        return ans;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size;
        System.out.print("Enter the size of the array: ");
        size = input.nextInt();
        int[] nums = new int[size];
        System.out.print("Enter the array, separated by a space: ");
        for (int i = 0; i < nums.length; i++) {
            nums[i] = input.nextInt();
        }
        System.out.print("Enter the target: ");
        int target = input.nextInt();
        System.out.println(threeSumClosest(nums, target));
    }
}
