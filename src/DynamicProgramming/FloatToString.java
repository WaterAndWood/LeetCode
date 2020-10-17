package DynamicProgramming;

/**
 *
 * 浮点数转化成字符串
 * 
 * @author Richa
 * @date 2020/10/17 21:27
 */
public class FloatToString {
    public String floatToString(float f) {
        StringBuilder intPart = new StringBuilder();
        float temp = f;
        while(!isLessOne(temp)) {
            int a = (int)temp % 10;
            intPart.append(a);
            temp -= a;
            temp /= 10;
        }
        String part1 = intPart.reverse().toString() + ".";
        StringBuilder dotPart = new StringBuilder();
        temp *= 10000000;
        while (!isLessOne(temp)) {
            int a = (int)temp % 10;
            dotPart.append(a);
            temp /= 10;
        }
        StringBuilder part2 = new StringBuilder();
        for (int i = 0; i < dotPart.length(); i++) {
            if (dotPart.charAt(i) != '0') {
                part2.append(dotPart.charAt(i));
            }
        }
        return part1 + part2.reverse().toString();
    }
    private boolean isLessOne(float f) {
        if (Math.abs(f) < 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        FloatToString floatToString = new FloatToString();
        System.out.println(floatToString.floatToString((float)72.052));
    }
}
