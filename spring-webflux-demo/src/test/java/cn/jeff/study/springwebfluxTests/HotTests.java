package cn.jeff.study.springwebfluxTests;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * @author swzhang
 * @date 2019/08/29
 */
public class HotTests {

    @Test
    public void testHot() throws InterruptedException {
        final Flux<Long> source = Flux.interval(Duration.ofMillis(1000))
                .take(10)
                .publish()
                .autoConnect();
        source.subscribe();
        Thread.sleep(5000);
        source
                .toStream()
                .forEach(System.out::println);
        //5,6,7,8,9
    }
}
