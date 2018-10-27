package cn.vivian.concurrent;

import lombok.Data;
import lombok.ToString;

@Data
public class StopThreadUnsafe {
    public static User u = new User();

    @Data
    @ToString
    public static class User {
        private int id = 0;
        private String name = "0";
    }

    public static class ChangeObjectThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (stopme) {
                    System.out.println("exit by stop me");
                    break;
                }
                synchronized (u) {
                    int v = (int)(System.currentTimeMillis()/1000);
                    u.setId(v);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    u.setName(String.valueOf(v));
                    Thread.yield();
                }
            }
        }

        volatile boolean stopme = false;
        public void stopMe() {
            stopme = true;
        }
    }

    public static class ReadObjectThread extends Thread{
        @Override
        public void run() {
            while (true) {
                synchronized (u) {
                    if (u.getId() != Integer.parseInt(u.name)) {
                        System.out.println(u.toString());
                    }
                }
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReadObjectThread().start();
        while (true) {
            Thread t = new ChangeObjectThread();
            t.start();
            Thread.sleep(150);
            t.stop();
        }
    }
}
