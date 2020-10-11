package DynamicProgramming;

/**
 *
 * 矩阵连乘：
 * 给定n个矩阵：A1,A2,...,An，其中Ai与Ai+1是可乘的，
 * i=1，2...，n-1。确定计算矩阵连乘积的计算次序，
 * 使得依此次序计算矩阵连乘积需要的数乘次数最少。
 * 输入数据为矩阵个数和每个矩阵规模，输出结果为计算矩阵连乘积的计算次序和最少数乘次数。
 *
 * 矩阵乘法满足结合律，故计算矩阵的连乘积可以有许多不同的计算次序。这种计算次序可以用加括号的方式来确定。
 * 不同的计算次序决定着计算量
 *
 * 计算三个矩阵连乘{A1，A2，A3}；维数分别为10*100 , 100*5 , 5*50
 * 此顺序(A1*A2)*A3: 10*100*5 + 10*5*50 = 7500
 * 此顺序A1*(A2*A3): 100*5*50 + 10*100*50 = 75000
 * 确定运算顺序使计算量最小化
 *
 * 转移方程：
 * 计算A[i:j] 1<=i<=j<=n，所需最少乘次数m[i,j]，则原问题的最优值为m[1,n]。
 * 当i=j时，A[i:j] = Ai，此时m[i:j] = 0, i = 1, 2, 3...n
 * 当i<j时，A[i:j]的最优次序在Ak,Ak+1之间断开，i<=k<j，则
 * m[i,j] = m[i,k] + m[k+1][j] + pi-1*pk*pj
 * k的位置不确定，所以去最小值min(i<=k<j)
 *
 * @author Richa
 * @date 2020/10/11 13:57
 */
public class MultiMatrix {
    public void multiMatrix(int[] p, int n, int[][] m, int[][] s) {
        for (int i = 1; i <= n; i++) {
            m[i][i] = 0;
        }
        /**
         * m[i,j]是从i到j的最优解
         * s[i][j]是i到j的断开索引k
         */
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                m[i][j] = m[i+1][j] + p[i-1]*p[i]*p[j];
                s[i][j] = i;
                for (int k = i + 1; k < j; k++) {
                    int t = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
                    if (t < m[i][j]) {
                        // 最小的值和位置k
                        m[i][j] = t;
                        s[i][j] = k;
                    }
                }
            }
        }
    }
    public void traceBack(int i, int j, int[][] s) {
        if (i == j) {
            return;
        }
        traceBack(i, s[i][j], s);
        traceBack(s[i][j]+1, j, s);
        System.out.println("Multiply  A" + i + "," + s[i][j] + "and A" + (s[i][j] + 1) + "," + j);
    }

    public static void main(String[] args) {
        System.out.println("我们测试结果：");
        MultiMatrix mc = new MultiMatrix();
        int n = 7;
        int p[] = { 30, 35, 15, 5, 10, 20, 25 };
        int m[][] = new int[n][n];
        int s[][] = new int[n][n];
        int l = p.length-1;
        mc.multiMatrix(p, l, m, s);
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                System.out.print(m[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                System.out.print(s[i][j]+" ");
            }
            System.out.println();
        }
        mc.traceBack( 1, 6, s);
    }
}
