package Backtracking;

import java.util.Scanner;

/**
 *
 * 牛客：雀魂启动
 * 回溯法，回溯最后一张牌和雀头的牌
 *
 * @author Richa
 * @date 2020/7/30 21:23
 */
public class Mahjong {
    private static int[] arr = new int[13];
    private static int[] count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        count = new int[9];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            ++count[arr[i] - 1];
        }
        int winCount = 0;
        for (int i = 1; i <= 9; i++) {
            if (count[i - 1] < 4) {
                /**
                 * 回溯最后一张牌，先加入数组构成14张牌，然后判断是否胡牌
                 */
                count[i - 1]++;
                if (win()) {
                    winCount++;
                    System.out.print(i + " ");
                }
                count[i - 1]--;
            }
        }
        if (winCount == 0) {
            System.out.print(0);
        }
    }

    public static boolean win() {
        /**
         * 从1~9中选择一个作为雀头，然后判断剩余的牌是否是4*3形式
         */
        for (int i = 1; i <= 9; i++) {
            if (count[i - 1] < 2) {
                continue;
            }
            /**
             * 回溯雀头的2张牌，递归剩下的牌是否组连续3张或者相同3张
             */
            count[i - 1] -= 2;
            if (hasTriple(4)) {
                count[i - 1] += 2;
                return true;
            }
            count[i - 1] += 2;
        }
        return false;
    }

    public static boolean hasTriple(int n) {
        if (n == 0) {
            return true;
        }
        for (int i = 1; i <= 9; i++) {
            if (count[i - 1] >= 3) {
                count[i - 1] -= 3;
                boolean subHasTriple = hasTriple(n - 1);
                count[i - 1] += 3;
                if (subHasTriple) {
                    return true;
                }
            }
            // 对应牌1~9的数组长度是9，last index = 8
            if (i <= 7 && count[i - 1] > 0 &&count[i] > 0 && count[i + 1] > 0) {
                count[i - 1]--;
                count[i]--;
                count[i + 1]--;
                boolean subHasTriple = hasTriple(n - 1);
                count[i - 1]++;
                count[i]++;
                count[i + 1]++;
                if (subHasTriple) {
                    return true;
                }
            }
        }
        return false;
    }

}
