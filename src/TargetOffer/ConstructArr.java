package TargetOffer;

/**
 *
 * Offer66: 构建乘积数组
 * 分为上三角和下三角，对角线为1
 *
 * @author Richa
 * @date 2022/4/16 23:46
 */
public class ConstructArr {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) {
            return new int[0];
        }
        int len = a.length;
        int[] b = new int[len];
        b[0] = 1;
        // B[i]的下三角各元素乘积乘入B[i]
        for (int i = 1; i < len; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }

        int temp = 1;
        for (int i = len - 2; i >= 0; i--) {
            // temp保存B[i]上三角元素乘积，乘入B[i]
            temp  *= a[i + 1];
            b[i] *= temp;
        }
        return b;
    }
}
