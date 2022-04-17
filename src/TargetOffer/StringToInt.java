package TargetOffer;

/**
 *
 * Offer67: 把字符串转换为整数
 * 考虑多种情况：
 * 1. 首尾空格删除
 * 2. 符号位：创建一个变量保存符号
 * 3. 非数字的字符返回
 * 4. 数字：字符转数字，此字符的ASCII码-0的ASCII码
 * 拼接 res = 10 * res + (ascii(c) - ascii('0')
 * 5. 越界问题，是否超过int边界，判断在此轮拼接后是否超过2147483647，若超过则加上符号位返回，拼接边界
 * 2147483647//10 = 214748364，对应两种情况：
 * res > 拼接边界，也就是10*res > 2147483650
 * res = 拼接边界，但(ascii(c) - ascii('0') > 7，拼接后是2147483648或者2147483649出现越界
 * 
 * @author Richa
 * @date 2022/4/17 10:00
 */
public class StringToInt {
    public int strToInt(String str) {
        if (str == null) {
            return 0;
        }
        char[] c = str.trim().toCharArray();
        if (c.length == 0) {
            return 0;
        }
        int res = 0, boundary = Integer.MAX_VALUE / 10;
        int i = 1, sign = 1;
        if (c[0] == '-') {
            sign = -1;
        } else if (c[0] != '+') {
            i = 0;
        }
        for (int j = i; j < c.length; j++) {
            // 检查是否是非数字
            if (c[j] > '9' || c[j] < '0') {
                break;
            }
            // 检查是否是越界
            if (res > boundary || (res == boundary && c[j] > '7')) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            res = 10 * res + (c[j] - '0');
        }
        return sign * res;
    }
}
