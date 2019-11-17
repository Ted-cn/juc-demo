package jvm;

import juc.ConditionDemo;

/**
 * 1 JVM 系统架构图
 * 2 类加载器（见 jvm.MyObject）
 * 3 Native
 *     3.1 native是一个关键字
 *     3.2 声明有，实现无，调用其他语言或操作系统底层实现
 * 4 PC寄存器
 *     记录了方法之间的调用和执行情况，类似排版值日表，用来存储指向下一条指令的地址，
 *     也即将要执行的指令代码。它是当前线程所执行的字节码的行号指示器。
 * 5 方法区
 *     5.1 供各线程共享的运行时内存区域。它存储了每个类的结构信息。
 *         例如运行时常量池（Runtime Constant Pool）、字段和方法数据、构造函数和普通方法的字节码内容。
 *     5.2 在不同的虚拟机里实现不完全相同，
 *         最典型的就是永久代（PermGen space, java7）和元空间（Metaspace, java8）。
 *     5.3 实例变量存在堆内存中，与方法区无关。
 *
 * 6 stack栈
 *     6.1 栈管运行，堆管存储。
 *     6.2 栈也叫栈内存，主管Java程序的运行，是在线程创建时创建，它的生命周期跟随线程的生命周期，
 *         线程结束栈内存也就释放，对于栈来说不存在垃圾回收问题，只要线程一结束该栈就结束，
 *         生命周期和线程一致，是线程私有的。
 *     6.3 8种基本类型的变量 + 对象的引用变量 + 实例方法都是在函数的栈内存中分配
 *     6.4 栈帧存储什么：
 *         本地变量（Local Variables）：输入参数和输出参数及方法内的变量；
 *         栈操作（Operand Stack）： 记录出栈、入栈的操作；
 *         栈帧数据（Frame Data）：包括类文件、方法等等。
 *
 *   队列（FIFO）：先进先出
 *   栈（FILO）:先进后出
 *
 * 7 heap堆
 *     新生区
 *         伊甸园区
 *         幸存者0区
 *         幸存者1区
 *     养老区
 *
 *     元空间
 *
 *     MinorGC的过程（复制 -> 清空 -> 互换）
 *
 *     首先，当Eden区满的时候会触发第一次GC，把还活着的对象拷贝到SurvivorFrom区，
 *     当Eden再次触发GC的时候会扫描Eden区和From区，对这两个区进行垃圾回收，
 *     经过这次回收后还存活的对象，则直接复制到To区（如果有对象的年龄已经达到了老年的标准，则赋值到养老区），
 *     同时把这些对象的年龄+1，清空Eden，SurvivorFrom
 *     然后，清空Eden和SurvivorFrom中的对象，也即复制之后有交换，谁空谁是To
 *     最后，SurvivorTo和SurvivorFrom互换，原SurvivorTo成为下一次GC时的SurvivorFrom。
 *     部分对象会在From区和To区中来回复制，如此来回15次（由JVM参数MaxTenuringThreshold决定，这个参数默认是15），
 *     最终如果还是存活状态，就存入老年代。
 */

public class JVMNote {

    public static void sayHello() {
        System.out.println("hello");
        /** java.lang.StackOverflowError */
        sayHello();
    }

    public static void sayHi() {

        while (true) {
            ConditionDemo conditionDemo = new ConditionDemo();
            System.out.println(conditionDemo.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}




















