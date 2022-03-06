package TargetOffer;

/**
 *
 * Offer20: 表示数值的数字
 *
 * @author Richa
 * @date 2022/3/6 16:31
 */
public class IsNumber {
    public boolean isNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.trim();
        boolean numFlag = false;
        boolean dotFlag = false;
        boolean eFlag = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                numFlag = true;
            } else if (s.charAt(i) == '.' && !dotFlag && !eFlag) {
                dotFlag = true;
            } else if (s.charAt(i) == 'e' || s.charAt(i) == 'E' && !eFlag && numFlag && (i != s.length() - 1)) {
                eFlag = true;
                numFlag = true;
            } else if ((s.charAt(i) == '+' || s.charAt(i) == '-')
                    && (i == 0 || s.charAt(i - 1) == 'e' || s.charAt(i - 1) == 'E') && (i != s.length() - 1)) {
                continue;
            } else {
                return false;
            }
        }
        return numFlag;
    }
}
