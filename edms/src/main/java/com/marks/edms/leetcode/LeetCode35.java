package com.marks.edms.leetcode;

/**
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 请必须使用时间复杂度为 O(log n) 的算法。
 *
 * 示例 1:
 * 输入: nums = [1,3,5,6], target = 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [1,3,5,6], target = 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: nums = [1,3,5,6], target = 7
 * 输出: 4
 */


public class LeetCode35 {
    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        int index = searchInsert(nums, target);
        System.out.println("返回的索引为:" + index);
    }

    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int ans = nums.length;

        while (left <= right) {
            int mid = (left + right)/2;
            if (target >= nums[mid]) {
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }


        return ans;
    }
}
