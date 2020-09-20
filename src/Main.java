import java.util.*;
public class Main {

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        sc.nextLine();
//        List<String> str = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            str.add(sc.nextLine());
//        }
//        for (int i = 0; i < n; i++) {
//            String s = str.get(i);
//            Set<String> set = getMap(i, str).get(i);
//            int j = 1;
//            for (; j <= s.length(); j++) {
//                if (!set.contains(s.substring(0, j))) {
//                    break;
//                }
//            }
//            System.out.println(s.substring(0, j));
//        }
//    }
//    private static HashMap<Integer, Set<String>> getMap(int i, List<String> list) {
//        Set<String> set = new HashSet<>();
//        for (int j = 0; j < list.size(); j++) {
//            if (j == i) {
//                continue;
//            }
//            String temp = list.get(j);
//            for(int k = 1; k <= temp.length(); k++) {
//                set.add(temp.substring(0, k));
//            }
//        }
//        HashMap<Integer, Set<String>> res = new HashMap<>();
//        res.put(i, set);
//        return res;
//    }
public boolean isPalindrome(int x) {
    if (x < 0) {
        return false;
    }
    String str = String.valueOf(x);
    int l = 0;
    int r = str.length() - 1;
    boolean res = true;
    while (l < r) {
        if (str.charAt(l) == str.charAt(r)) {
            l++;
            r--;
            continue;
        } else {
            res = false;
            break;
        }

    }
    return res;
}
public static void main(String[] args) {
    String num1 = "547";
    String num2 = "123";
    System.out.println(num2.compareTo(num1));

}
}
