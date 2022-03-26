package TargetOffer;

/**
 * @author wangzhen
 * @creatTime 2022/3/26 10:51 上午
 * @description Offer38扩展：输入一个含有8个数字的数组，判断有没有可能把这8个数字分别放到正方体的8个顶点上，使得
 * 正方体上三组相对的面上的4个顶点的和都相等
 * 找出这8个数字的全排列，然后验证每种全排列是否满足三对面上的和相等
 */
public class CubePermutation {
    private static boolean checking(int[] data) {
        int r1 = data[0] + data[1] + data[2] + data[3];
        int r2 = data[4] + data[5] + data[6] + data[7];
        int r3 = data[0] + data[2] + data[4] + data[6];
        int r4 = data[1] + data[3] + data[5] + data[7];
        int r5 = data[0] + data[1] + data[4] + data[5];
        int r6 = data[2] + data[3] + data[6] + data[7];
        if (r1 == r2 && r3 == r4 && r5 == r6) {
            return true;
        } else {
            return false;
        }
    }

    public void permutation(int[] data) {
        if (data == null || data.length == 0 || data.length != 8) {
            return;
        }
        System.out.println(dfs(data, 0, data.length - 1));
    }

    public boolean dfs(int[] data, int start, int end) {
        if (start == end && checking(data)) {
            return true;
        }
        for (int i = start; i < end; i++) {
            swap(data, i, start);
            if (dfs(data, i + 1, end)) {
                return true;
            }
            swap(data, start, i);
        }
        return false;
    }

    private static void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public static void main(String[] args) {
        int[] array1 = {1,2,3,4,5,6,7,8};
        int[] array2 = {1,2,3,2,3,2,1,2};
        CubePermutation cubePermutation = new CubePermutation();
        cubePermutation.permutation(array1);
        cubePermutation.permutation(array2);
    }
}
