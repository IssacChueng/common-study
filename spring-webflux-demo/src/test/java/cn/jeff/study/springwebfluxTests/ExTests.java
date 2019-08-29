package cn.jeff.study.springwebfluxTests;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author swzhang
 * @date 2019/08/29
 */
public class ExTests {

    @Test
    public void testSubscribe() {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalAccessException()))
                .subscribe(System.out::println, System.err::println);
    }

    @Test
    public void testOnErrorReturn() {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalAccessException()))
                .onErrorReturn(0)
                .subscribe(System.out::println);
    }

    @Test
    public void testOnSwitchError() {
        //switchOnError 已经没有了
    }

    @Test
    public void testOnErrorResumeWith() {
        // onErrorResumeWith api 没了
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .onErrorResume(e -> {
                    if (e instanceof IllegalArgumentException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalStateException) {
                        return Mono.just(1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);
    }

    @Test
    public void testRetry() {
        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalArgumentException()))
                .retry(1)
                .onErrorResume(e -> {
                    if (e instanceof IllegalArgumentException) {
                        return Mono.just(0);
                    } else if (e instanceof IllegalStateException) {
                        return Mono.just(1);
                    }
                    return Mono.empty();
                })
                .subscribe(System.out::println);
    }

}
