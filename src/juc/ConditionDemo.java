package juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



class ShareData {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public int getNumber(int a) {
        int b;
        switch (a) {
            case 1: b = 2;
                break;
            case 2: b = 3;
                break;
            default:b = 1;
                break;
        }
        return b;
    }

    public Condition getCondition(int number) {
        Condition condition;
        switch (number) {
            case 1: condition = condition1;
                break;
            case 2: condition = condition2;
                break;
            default: condition = condition3;
                break;
        }
        return condition;
    }

    public void myPrint(int _number, int times) {
        lock.lock();
        Condition _condition = getCondition(_number);
        try {
            while (number != _number) {
                _condition.await();
            }
            for (int i = 0; i < times; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + times + "\t" + String.valueOf(i));
            }
            number = getNumber(_number);
            getCondition(number).signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.myPrint(1, 5);
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.myPrint(2, 10);
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareData.myPrint(3, 15);
            }
        }, "C").start();
    }
}
