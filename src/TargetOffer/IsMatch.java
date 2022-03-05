package TargetOffer;

/**
 * @author wangzhen
 * @creatTime 2022/3/5 3:51 下午
 * @description Offer19: 正则表示式匹配
 */
public class IsMatch {
    public boolean isMatch(String s, String p) {
        // 正则串p是空，字符串不空，false；而字符串空，正则串p是否为空无法决定是否匹配：.*
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        boolean headMatched = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // 跳过正则匹配或者继续匹配正则
            // headMatched参与记住了第一个字符的匹配结果，解决s: ab p: .*c
            return isMatch(s, p.substring(2)) || (headMatched && isMatch(s.substring(1), p));
        } else if (headMatched) {
            return isMatch(s.substring(1), p.substring(1));
        } else {
            return false;
        }
    }

}
