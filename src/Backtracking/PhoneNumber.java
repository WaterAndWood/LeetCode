package Backtracking;

import java.util.*;

/**
 *
 * LeetCode 17: 电话号码的字母组合
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

    public static void main(String[] args) {
        PhoneNumber pn = new PhoneNumber();
        List<String> ans = pn.letterCombinations("23");
        System.out.println(ans);
    }
}
