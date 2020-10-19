package BinarySearch;

/**
 *
 * 二分法求开方根
 *
 * @author Richa
 * @date 2020/10/19 23:37
 */
public class BinarySqrt {

    public static float sqrt(int a) {
        if (a < 0) {
            throw new NumberFormatException("a > 0");
        }
        double l = 0;
        double r = a;
        double mid = (l + r) / 2.0;
        while (true) {
            if (isEqual(a, mid * mid) == 0) {
                return (float)mid;
            } else if (isEqual(a, mid * mid) > 0) {
                r = mid;
                mid = (l + r) / 2.0;
            } else {
                l = mid;
                mid = (l + r) / 2.0;
            }
        }
    }

    public static int isEqual(double x, double y) {
        // 自定义精度
        double d = 0.01;
        if (x - y < -d) {
            return 1;
        } else if (x - y > d) {
            return -1;
        } else {
            return 0;
        }
    }

    // 牛顿法
    public static int mysqart(int a) {
        long x = a;
        while (x * x > a) {
            x = (x + a / x) / 2;
        }
        return (int) x;
    }

    public static void main(String[] args) {
        float ans = sqrt(2);
        System.out.println(ans);
        int res = mysqart(3);
        System.out.println(res);
    }
}
