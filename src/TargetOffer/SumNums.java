package TargetOffer;

/**
 *
 * Offer64: 求1+2+...+n
 *
 * @author Richa
 * @date 2022/4/16 22:59
 */
public class SumNums {
    /**
     * 递归使用到if判断终止条件，也不适合
     */
    public int sumNumsRecur(int n) {
        if (n == 1) {
            return 1;
        }
        n += sumNumsRecur(n-1);
        return n;
    }

    /**
     * 逻辑短路效应：&& false会短路，|| true会短路
     * 基于逻辑短路效应终止递归
     */
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
