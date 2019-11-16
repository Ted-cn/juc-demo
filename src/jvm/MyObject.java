package jvm;

/**
 * 类加载器
 * 一、 虚拟机自带的加载器
 *     1. 启动类加载器(Bootstrap) C++
 *        $JAVAHOME/jre/lib/rt.jar
 *     2. 扩展类加载器（Extension）Java
 *        $JAVAHOME/jre/lib/ext/*.jar
 *     3. 应用程序类加载器（AppClassLoader），Java也叫系统类加载器，加载当前应用的classpath的所有类
 *        $CLASSPATH
 *
 * 二、 用户自定义加载器
 *     Java.lang.ClassLoader的子类，用户可以定制类的加载方式。
 *
 * 双亲委派：
 *     当一个类加载器收到了类加载请求，它首先不会尝试自己去加载这个类，而是把这个请求委派给父类去完成。
 * 每一个层次的类加载器都是如此，因此所有的加载请求都应该传送到启动类加载器中，只有当父类加载器反馈自己
 * 无法完成这个请求的时候（在它的加载路径下没有找到所需加载的Class），子类加载器才会尝试自己去加载。
 *
 * 采用双亲委派的一个好处是，比如加载位于rt.jar包中的类java.lang.Object，不管是哪个加载器加载这个类，
 * 最终都是委托顶层的启动类加载器进行加载，这样就保证了使用不同的类加载器最终得到的都是同样一个Object
 * 对象。
 */

public class MyObject {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();

        Object object = new Object();


        System.out.println(object.getClass().getClassLoader());
        System.out.println(myObject.getClass().getClassLoader());

    }
}
