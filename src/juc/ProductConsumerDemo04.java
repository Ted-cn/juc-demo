package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition {
    int number = 0;

    public synchronized void incre() throws Exception {
        while (number > 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
    public synchronized void decre() throws Exception {
        while (number <= 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        this.notifyAll();
    }
}

class Computer {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    
    public void incre() throws Exception {
        lock.lock();
        System.out.println(1);
        try {
            while (number > 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
    
    public void decre() throws Exception {
        lock.lock();
        try {
            while (number <= 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "\t" + number);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

public class ProductConsumerDemo04 {

    public static void main(String[] args) throws Exception {

        /**
         * 多个线程加1减1交替执行10次
         */
        AirCondition airCondition = new AirCondition();
        Computer computer = new Computer();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    computer.incre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "加A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    computer.incre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "加B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    computer.decre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "减A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    computer.decre();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "减B").start();
    }
}
