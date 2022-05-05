package Backtracking;

import java.util.*;

/**
 *
 * LeetCode 17: 电话号码的字母组合
 * 当出现组合的字眼时，首先反应使用回溯
 * 本质是在搜索一个递归树
 * 
 * @author Richa
 * @date 2020/9/11 22:54
 */
public class PhoneNumber {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return ans;
        }
        // 回溯的递归也可以视为循环
        traceBack(new StringBuilder(), digits, ans);
        return ans;
    }

    private void traceBack(StringBuilder sb, String digits, List<String> ans) {
        // 回溯终止条件
        if (digits.length() == 0) {
            ans.add(sb.toString());
        } else {
            String firstDigit = digits.substring(0, 1);
            String number = phone.get(firstDigit);
            for (int i = 0; i < number.length(); i++) {
                // 数字对应字符串的字符，每个字符后面加上下一个数字对应字符串的字符
                String letter = number.substring(i, i + 1);
                sb.append(letter);
                traceBack(sb, digits.substring(1), ans);
                int len = sb.length();
                // 回溯，删除之前加入的letter
                sb.delete(len - 1, len);
            }
        }
    }

    /**
     * 回溯（深度优先遍历搜索所有解）
     * liweiwei
     */
    public List<String> letterCombine(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        /**
         * 映射数字和字母，index=0对应数字2
         */
        String[] digitMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        findCombinations(digits, digitMap, 0, "", res);
        return res;
    }

    /**
     *
     * @param digits 原始字符串
     * @param digitMap 映射关系
     * @param start 从原始字符串第几位开始搜索
     * @param pre 已经得到的字符串
     */
    private void findCombinations(String digits, String[] digitMap, int start, String pre, List<String> res) {
        if (start == digits.length()) {
            res.add(pre);
            return;
        }
        String number = digitMap[digits.charAt(start) - '2'];
        for (int i = 0; i < number.length(); i++) {
            /**
             * 字符追加到后面是新建一个字符串，不需要显式回溯
             */
            findCombinations(digits, digitMap, start + 1, pre + number.charAt(i), res);
        }
    }

    /**
     * 广度优先搜索
     */
    public List<String> findCombinationBfs(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }

        String[] digitMap = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        /**
         * 新的一轮是在上一轮的基础上追加
         */
        for (int i = 0; i < digits.length(); i++) {
            int num = digits.charAt(i) - '2';
            String strList = digitMap[num];
            List<String> cur = new ArrayList<>();
            for (String s : res) {
                for (char c : strList.toCharArray()) {
                    cur.add(s + c);
                }
            }
            res = cur;
        }
        return res;
    }


    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber();
        List<String> ans = pn.letterCombinations("23");
        System.out.println(ans);
    }
}
