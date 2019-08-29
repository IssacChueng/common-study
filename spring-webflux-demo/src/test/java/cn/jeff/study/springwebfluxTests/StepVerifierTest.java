package cn.jeff.study.springwebfluxTests;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * @author swzhang
 * @date 2019/08/29
 */
public class StepVerifierTest {

    @Test
    public void testStepVerifier() {
        StepVerifier.create(Flux.just("a", "b"))
                .expectNext("a")
                .expectNext("b")
                .verifyComplete();
    }

    @Test
    public void testTime() {
        StepVerifier.withVirtualTime(() -> Flux.interval(Duration.ofHours(4), Duration.ofDays(1)).take(2))
                .expectSubscription()
                .expectNoEvent(Duration.ofHours(4))
                .expectNext(0L)
                .thenAwait(Duration.ofDays(2))
                .expectNext(1L)
                .verifyComplete();
    }
}
