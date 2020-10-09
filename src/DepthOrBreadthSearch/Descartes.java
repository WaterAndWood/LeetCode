package DepthOrBreadthSearch;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 笛卡尔乘积：在数学中，两个集合X和Y的笛卡儿积（Cartesian product），又称直积，
 * 表示为X × Y，第一个对象是X的成员而第二个对象是Y的所有可能有序对的其中一个成员。
 *
 * 假设集合A={a,b}，集合B={0,1,2}，则两个集合的笛卡尔积为(a,0),(a,1),(a,2),(b,0),(b,1), (b,2)
 * 
 * @author Richa
 * @date 2020/10/9 23:46
 */
public class Descartes {

    /**
     * dimValue入参，结果放在result
     * curList每次笛卡尔积的结果
     * layer 在dimValue中的层数
     */
    private static void recursive(List<List<String>> dimValue, List<List<String>> result, int layer,
                                  List<String> curList) {
        if (layer < dimValue.size() - 1) {
            // 当前层为空，直接跳过
            if (dimValue.get(layer).size() == 0) {
                recursive(dimValue, result, layer + 1, curList);
            } else {
                // 当前层元素依次加入上一层curList，逐层递归
                for (int i = 0; i < dimValue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<>(curList);
                    list.add(dimValue.get(layer).get(i));
                    recursive(dimValue, result, layer + 1, list);
                }
            }
        } else if (layer == dimValue.size() - 1) {
            // 最后一层为空，直接加入结果
            if (dimValue.get(layer).size() == 0) {
                result.add(curList);
            } else {
                // 递归到最后一层，最后一层的每个元素add到上一层curList的后面
                for (int i = 0; i < dimValue.get(layer).size(); i++) {
                    List<String> list = new ArrayList<>(curList);
                    list.add(dimValue.get(layer).get(i));
                    result.add(list);
                }
            }
        }
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>();
        list1.add("1");
        list1.add("2");
        List<String> list2 = new ArrayList<String>();
        list2.add("a");
        list2.add("b");
        List<String> list3 = new ArrayList<String>();
        list3.add("3");
        list3.add("4");
        list3.add("5");
        List<String> list4 = new ArrayList<String>();
        list4.add("c");
        list4.add("d");
        list4.add("e");
        List<List<String>> dimValue = new ArrayList<>();
        dimValue.add(list1);
        dimValue.add(list2);
        dimValue.add(list3);
        dimValue.add(list4);

        List<List<String>> recursiveResult = new ArrayList<List<String>>();
        // 递归实现
        recursive(dimValue, recursiveResult, 0, new ArrayList<String>());
        System.out.println("递归实现笛卡尔乘积: 共 " + recursiveResult.size() + " 个结果");
        for (List<String> list : recursiveResult) {
            for (String string : list) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
