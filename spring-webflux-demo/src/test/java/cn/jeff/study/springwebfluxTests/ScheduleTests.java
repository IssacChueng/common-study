package cn.jeff.study.springwebfluxTests;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @author swzhang
 * @date 2019/08/29
 */
public class ScheduleTests {

    @Test
    public void testSchedule() {
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                //.subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);

        //Why
    }


}
