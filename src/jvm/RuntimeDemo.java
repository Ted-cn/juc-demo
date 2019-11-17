package jvm;

import java.util.Random;

public class RuntimeDemo {

    public static void main(String[] args) {
        // java虚拟机试图使用的最大内存量
        // 1806 1/4
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
        // java虚拟机中的内存问题
        // 123 1/64
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        // -Xms1024m -Xmx1024m -XX:+PrintGCDetails
        String str = "hello";
        //System.out.println(str);
        while (true) {
            str += str + new Random().nextInt(888888) + new Random().nextInt(99999);
        }
    }
}
