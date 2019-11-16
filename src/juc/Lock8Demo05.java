package juc;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendA() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("A");
    }

    public synchronized void sendB() throws Exception {
        System.out.println("B");
    }

    public void sayHello() throws Exception  {
        System.out.println("hello");
    }
}
public class Lock8Demo05 {

    public static void main(String[] args) throws Exception {
        /**
         * 1. 标准访问：A B
         * 2. A暂停3秒：A B
         * 3. 新增普通sayHello()方法: hello A
         * 4. 两个手机: B A
         * 5. 两个静态同步方法，一部手机: A B
         * 6. 两个静态同步方法，两部手机: A B
         * 7. 1个静态同步方法，一个同步方法，一部手机: B A
         * 8. 1个静态同步方法，一个同步方法，一部手机: B A
         */
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                phone.sendA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "a").start();
        TimeUnit.MILLISECONDS.sleep(1);
        new Thread(() -> {
            try {
                //phone.sendB();
                //phone.sayHello();
                phone2.sendB();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "b").start();
    }

}
