package juc;

import java.util.concurrent.*;

/**
 * 线程池做的工作主要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，
 * 如果数量超过了最大数量，超出数量的线程将排除等候，等其他线程执行完毕，再从队列中取出任务来执行。
 *
 * 主要特点：线程利用；控制最大并发数；管理线程。
 *
 * 第一：降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。
 * 第二：提高响应速度。当任务到达时，任务可以不需要等到线程创建就能立即执行。
 * 第三：提高线程的可管理性。线程是稀缺资源，如果无限制地创建，不仅会消耗系统资源，还会降低系统的稳定性，
 *      使用线程池可以进行统一的分配，调优和监控。
 */
public class MyThreadPoolDemo {

    public static void main(String[] args) {
        // 固定线程数量池
        //ExecutorService threadPool = Executors.newFixedThreadPool(50);
        // 单线程池
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 按需分配线程数量池
        //ExecutorService threadPool = Executors.newCachedThreadPool();
        /**
         * corePoolSize: 线程中的常驻核心线程数
         * maximumPoolSize: 线程池能够容纳同时执行的最大线程数，此值必须大于等于1
         * keepAliveTime: 空闲线程的存活时间
         * unit: keepAliveTime的单位
         * workQueue: 任务队列，被提交但尚未被执行的任务
         * threadFactory: 生成线程池中工作线程的线程工厂，用于创建线程，一般默认即可
         * handler: 拒绝策略，表示当队列满了并且工作线程大于等于线程池最大线程数时，
         *          如何拒绝
         */
        ExecutorService threadPool = new ThreadPoolExecutor(0,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>()
        );
        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() +"\t 办理业务");
                });
                //TimeUnit.MILLISECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}

























