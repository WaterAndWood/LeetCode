package Leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Leetcode6: z字形变换
 * 模拟打印过程，使用flag表示在z字形转折时反向
 * 
 * @author Richa
 * @date 2022/4/29 19:44
 */
public class ZPrint {
    public String convert(String s, int numRows) {
        if (s == null || s.length() == 0) {
            return s;
        }
        if (numRows < 2) {
            return s;
        }

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }

        int i = 0, flag = -1;
        // 到达第一行和第numRows行的时候转向打印
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = -flag;
            }
            i += flag;
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder row : rows) {
            res.append(row);
        }
        return res.toString();
    }
}
