package TargetOffer;

/**
 * 单例模式的实现 DCL(doble check locking)
 * 优点：线程安全，lazy loading，效率较高
 *
 * @author Richa
 * @date 2022/2/16 22:42
 */
public class SingletonDemo {
    /**
     * volatile禁止指令重排序，防止singletonDemo未初始化完成时进行访问导致NPE
     * 初始化操作并非原子操作
     */
    private static volatile SingletonDemo singletonDemo;
    private SingletonDemo(){}

    /**
     * 两次判空防止多进程同时进入锁内部，导致多次初始化
     */
    public SingletonDemo getSingletonDemo() {
        if (singletonDemo == null) {
            synchronized (SingletonDemo.class) {
                if (singletonDemo == null) {
                    return new SingletonDemo();
                }
            }
        }
        return singletonDemo;
    }
}

/**
 *
 * 饿汉式，静态常量或者静态代码块
 *
 * @author Richa
 * @date 2022/2/16 23:01
 */
class SingletonDemo1 {
    /**
     * 优点：类加载时完成实例化，线程安全
     * 缺点：没有lazy loading，内存浪费
     */
    private static SingletonDemo1 instance = new SingletonDemo1();
    private SingletonDemo1() {}
    public static SingletonDemo1 getInstance() {
        return instance;
    }

    /**
     * 优点：类实例化放在静态代码块中，线程安全
     * 缺点：没有lazy loading，内存浪费
     */
    private static SingletonDemo1 instance1;
    static {
        instance1 = new SingletonDemo1();
    }
    public static SingletonDemo1 getInstance1() {
        return instance1;
    }
}

/**
 *
 * 登记式：静态内部类实现单例
 * 优点：类加载机制保证线程安全，lazy loading，效率高
 * 
 * @author Richa
 * @date 2022/2/16 23:37
 */
class SingletonDemo2 {
    private static class SingletonHolder {
        private static final SingletonDemo2 INSTANCE = new SingletonDemo2();
    }

    private SingletonDemo2() {}

    public static SingletonDemo2 getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

/**
 *
 * 枚举实现单例，自动支持序列化机制
 * 
 * @author Richa
 * @date 2022/2/16 23:47
 */
enum SingletonDemo4 {
    INSTANCE;
    public void whateverMethod() {}
}
