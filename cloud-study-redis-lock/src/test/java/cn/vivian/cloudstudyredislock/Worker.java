package cn.vivian.cloudstudyredislock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Worker implements Runnable {

    private final CountDownLatch startSignal;

    private final CountDownLatch doneSignal;

    private DistributedLocker distributedLocker;

    public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, DistributedLocker distributedLocker) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
        this.distributedLocker = distributedLocker;
    }

    public void setDistributedLocker(DistributedLocker distributedLocker) {
        this.distributedLocker = distributedLocker;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            distributedLocker.lock("test", new AquiredLockWorker<Object>() {
                @Override
                public Object invokeAfterLockAquire() throws Exception {
                    doTask();
                    return null;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doTask() {
        System.out.println(Thread.currentThread().getName() + "start");
        Random random = new Random();
        int _int = random.nextInt(100);
        System.out.println(Thread.currentThread().getName() + " sleep " + _int + "mills");
        try {
            Thread.sleep(_int);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
        doneSignal.countDown();
    }
}
