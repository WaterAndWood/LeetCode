package PrefixSum;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * LeetCode 560: 和为K的子数组
 * 前缀和：对于数组A，如果数组B[i]=A[i]+A[i-1]+...+A[0]，则B就是A的前缀和数组。
 * 子数组的和可以用前缀和数组某两个元素的差来计算：A[i]+A[i+1]+...A[j] = B[j]-B[i-1]
 * 如果固定住结尾B[j]，想要A[i]+A[i+1]+...A[j]=k，那么一定有B[i-1]=B[j]-k
 *
 * LeetCode 1546: 和为目标值的最大数目不重叠非空子数组数目
 *
 * 牛客网：病毒检测
 * 检查连续子串中1的个数。如果子串内容相同，但是开始或者结束位置不一样，则被认为是不同的子串。
 *
 * 第一行是一个整数k，表示子串中有k个1就有可能是病毒。其中 0 <= k <= 1 000 000
 *
 * 第二行是一个字符串，就是library的代码部分的二进制表示。字符串长度 <= 1 000 000。并且字符串中只包含"0"或"1".
 *
 * @author Richa
 * @date 2020/8/9 17:28
 */
public class SubArraySum {
    /**
     * 枚举法
     *
     */
    public int subArraySum1(int[] nums, int k) {
        int count = 0;
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subArraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        // 构造前缀和数组
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 前缀和加哈希表优化
     *
     */
    public int subArraySum3(int[] nums, int k) {
        // key：前缀和，value：key 对应的前缀和的个数
        Map<Integer, Integer> preSumMap = new HashMap<>();
        // 对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumMap.put(0, 1);
        int count = 0;
        int preSum = 0;
        for (int n : nums) {
            preSum += n;
            if (preSumMap.containsKey(preSum - k)) {
                count += preSumMap.get(preSum - k);
            }
            preSumMap.put(preSum, preSumMap.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }

    /**
     * 不重叠，所以在遍历满足条件时，重置sum 和map
     *
     */
    public int maxNonOverlapping(int[] nums, int target) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int count = 0;
        for (int n : nums) {
            sum += n;
            if (map.containsKey(sum - target)) {
                count++;
                sum = 0;
                map = new HashMap<>();
                map.put(0, 1);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        /**
         * 前缀和+哈希表
         *
         */
        Scanner sc = new Scanner(System.in);
        String l1 = sc.nextLine();
        String l2 = sc.nextLine();
        int k = Integer.valueOf(l1);
        char[] nums = l2.toCharArray();
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int num = 0;
        long ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == '1') {
                num++;
            }
            if (map.containsKey(num - k)) {
                ans += map.get(num - k);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        System.out.println(ans);
    }

}
