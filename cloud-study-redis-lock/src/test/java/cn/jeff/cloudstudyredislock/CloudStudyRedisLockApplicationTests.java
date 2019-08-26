package cn.jeff.cloudstudyredislock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloudStudyRedisLockApplicationTests {

    @Autowired
    RedisLocker distributedLocker;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testRedlock() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch endSignal = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Worker(startSignal, endSignal, distributedLocker)).start();
        }

        startSignal.countDown();
        endSignal.await();

        System.out.println("All processors done. Shutdown connection");
    }

}
