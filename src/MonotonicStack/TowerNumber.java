package MonotonicStack;
import java.util.*;
/**
 *
 * 牛客：逛街
 * 他在每栋楼的位置处能看到多少栋楼呢？（当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 * 输入第一行将包含一个数字n，代表楼的栋数，接下来的一行将包含n个数字wi(1<=i<=n)，代表每一栋楼的高度。
 * 
 * @author Richa
 * @date 2020/9/20 10:16
 */
public class TowerNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = sc.nextInt();
        }
        // 保存向右看的到的数量，从右向左遍历，利用单调栈将看到的数量保存在rightLook中
        int[] rightLook = new int[len];
        // stack中要保存的是能看见的楼的 index
        Deque<Integer> stack = new LinkedList<>();
        // 从右向左过程中不能出现单调增
        for (int i = len - 1; i >= 0; i--) {
            rightLook[i] = stack.size();
            while (!stack.isEmpty() && (arr[i] >= arr[stack.peek()])) {
                // 去掉后面比当前楼矮的楼
                stack.pop();
            }
            stack.push(i);
        }
        stack.clear();
        // 从左向右遍历，获取向左看到的计数
        for (int i = 0; i < len; i++) {
            int total = rightLook[i] + 1 + stack.size();
            while (!stack.isEmpty() && (arr[i] >= arr[stack.peek()])) {
                stack.pop();
            }
            System.out.print(total + " ");
            stack.push(i);
        }
    }
}
