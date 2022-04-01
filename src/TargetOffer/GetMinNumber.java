package TargetOffer;

/**
 *
 * Offer45: 把数排成最小的数
 * 是一个排序问题，按照以下的拼接规则进行排序：
 * 如果 x+y > y+x，x 大于 y；如果 x+y < y+x，x < y
 * @author Richa
 * @date 2022/3/30 23:02
 */
public class GetMinNumber {
    public String minNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }

        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
//        quickSort(strs, 0, strs.length - 1);
        sort(strs, 0, strs.length - 1);
        StringBuilder ans = new StringBuilder();
        for(String s : strs) {
            ans.append(s);
        }
        return ans.toString();

    }

    private void quickSort(String[] strs, int left, int right) {
        if (left < right) {
            int pivot = partiton(strs, left, right);
            quickSort(strs, left, pivot);
            quickSort(strs, pivot + 1, right);
        }
    }

    private int partiton(String[] strs, int left, int right) {
        String temp = strs[left];
        int pivot = left;
        while (left < right) {
            while (left < right && (strs[right] + strs[pivot]).compareTo(strs[pivot] + strs[right]) > 0) {
                right--;
            }
            strs[left] = strs[right];
            while (left < right && (strs[left] + strs[pivot]).compareTo(strs[pivot] + strs[left]) < 0) {
                left++;
            }
            strs[right] = strs[left];
        }
        strs[left] = temp;
        return left;
    }

    private void sort(String[] strs, int left, int right) {
        if (left < right) {
            int pivot = partitonSwap(strs, left, right);
            sort(strs, left, pivot - 1);
            sort(strs, pivot + 1, right);
        }
    }

    private int partitonSwap(String[] strs, int left, int right) {
        int pivot = right;
        int p = left - 1;
        for (int i = left; i < right; i++) {
            if ((strs[i] + strs[pivot]).compareTo(strs[pivot] + strs[i]) < 0) {
                p++;
                // 把后面的i换到前面的p，p < i，p在i前面
                swap(strs, p, i);
            }
        }
        swap(strs, p + 1, right);
        return p + 1;
    }

    private void swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = strs[i];
    }
}
