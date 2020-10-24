package DepthOrBreadthSearch;

/**
 *
 * LeetCode 678: 有效的括号字符串
 * Dfs算法：耗时长
 * count 记录左括号个数, 遇左则增，遇右则减，若不够减则 return false
 * 
 * @author Richa
 * @date 2020/10/22 18:36
 */
public class BracketsStringMatch {
    public boolean checkValidString(String s) {
        return check(s, 0, 0);
    }

    private static boolean check(String s, int start, int count) {
        if (count < 0) {
            return false;
        }

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                if (count-- == 0) {
                    return false;
                }
            } else if (c == '*') {
                /**
                 * *分别作为 (，)抵消一个左括号，空
                 *
                 */
                return check(s, i + 1, count + 1) ||
                        check(s, i + 1, count - 1) ||
                        check(s, i + 1, count);
            }
        }
        return count == 0;
    }

    /**
     * 贪心算法：左括号至少几个，至多几个
     * 遇到( 时，左括号至多、至少都增加
     * 遇到) 时，左括号至多、至少都减少
     * 遇到* 时，可能增加、减少、不变，所以至多增加，至少减少
     *
     * 至多max 小于0 时，返回false，说明左括号数量小于0
     * 最后左括号数量== 0
     *
     */
    private static boolean checkIfValid(String s) {
        int min = 0, max = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                if (min > 0) {
                    min--;
                }
                if (max-- == 0) {
                    return false;
                }
            } else {
                if (min > 0) {
                    min--; // 作为右括号
                }
                max++; // 作为左括号
            }
        }
        return min == 0;
    }
}
