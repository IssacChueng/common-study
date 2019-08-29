package cn.jeff.study.springwebfluxTests;

import org.junit.Test;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

/**
 * @author swzhang
 * @date 2019/08/29
 */
public class PublishTests {

    @Test
    public void testPublisher() {
        final TestPublisher<String> testPublisher = TestPublisher.create();
        testPublisher.next("a");
        testPublisher.next("b");
        testPublisher.complete();

        testPublisher.flux().subscribe(System.out::println);

        StepVerifier.create(testPublisher)
                .expectNext("a")
                .expectNext("b")
                .expectComplete();

        //下面的也不报错
        StepVerifier.create(testPublisher)
                .expectNext("b")
                .expectNext("a")
                .expectComplete();
    }
}
