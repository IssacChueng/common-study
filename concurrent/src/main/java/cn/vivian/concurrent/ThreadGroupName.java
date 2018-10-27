package cn.vivian.concurrent;

public class ThreadGroupName implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup tg = new ThreadGroup("PrintGroup");
        Thread t1 = new Thread(tg, new ThreadGroupName(), "T1");
        Thread t2 = new Thread(tg, new ThreadGroupName(), "T2");
        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(tg.activeCount());
        tg.list();
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                wait(10000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
