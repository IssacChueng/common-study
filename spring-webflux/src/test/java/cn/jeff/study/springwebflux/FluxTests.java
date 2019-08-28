package cn.jeff.study.springwebflux;

import org.junit.Test;
import org.reactivestreams.Publisher;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.time.Duration;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author swzhang
 * @date 2019/08/26
 */
public class FluxTests {

    @Test
    public void test0() throws InterruptedException {
        Flux.just("123", "2").subscribe(System.out::println);
        List<String> array = new ArrayList<>();
        array.add("123");
        array.add("2");
        Flux.fromIterable(array).subscribe(System.out::println);

        Flux.empty().subscribe(System.out::println);
        Flux.empty().subscribe(null, null, () -> System.out.println("complete"));

        //Flux.error(new EmptyStackException()).subscribe(System.out::println);
        Flux.never().subscribe(var -> System.out.println("object" + var), var -> var.printStackTrace(), () -> System.out.println("never complete"));
        Flux.range(0, 10).subscribe(System.out::println);

        Flux.interval(Duration.ofSeconds(5)).subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(20);

    }

    @Test
    public void test1() {
        Flux.generate(sink -> {
            sink.next("Hello");
            sink.complete();
        }).subscribe(System.out::println);


        final Random random = new Random();
        Flux.generate(ArrayList::new, (list, sink) -> {
            int value = random.nextInt(100);

            list.add(value);
            sink.next(value);
            if (list.size() == 10) {
                sink.complete();
            }
            return list;
        }).subscribe(System.out::println);
    }

    @Test
    public void testCreate() {
        Flux.create(sink -> {
            for (int i = 0; i < 10; i++) {
                sink.next(i);
            }
            sink.complete();
        }).subscribe(System.out::println);
    }

    @Test
    public void testMono() throws InterruptedException {
        Mono.fromCallable(() ->  "123").subscribe(System.out::println);

        //500ms后产生0
        Mono.delay(Duration.ofMillis(500)).subscribe(System.out::println);

        //忽略
        Mono.ignoreElements(Mono.empty()).subscribe(i -> System.out.println("1"));

        TimeUnit.SECONDS.sleep(1);

    }

    @Test
    public void testOpBuffer() throws InterruptedException {
        Flux.range(1, 10)
                .buffer(5)
                .subscribe(System.out::println);
        System.out.println("----------");
        Flux.interval(Duration.ofSeconds(1))
                .bufferTimeout(500, Duration.ofMillis(333))
                .subscribe(System.out::println);

        TimeUnit.SECONDS.sleep(10);

        Flux.interval(Duration.ofSeconds(1))
                .bufferTimeout(2, Duration.ofMillis(333))
                .take(2)
                .toStream()
                .forEach(System.out::println);
    }

    @Test
    public void testOpFilter() {
        Flux.range(1, 100)
                .filter(i -> i % 2 == 0)
                .subscribe(System.out::println);
    }

    @Test
    public void testOpWindow() {
        Flux.range(1, 100)
                .window(20)
                .subscribe(System.out::println);

        Flux.interval(Duration.ofMillis(100))
                .window(Duration.ofMillis(1001))
                .take(2)
                .toStream()
                .forEach(System.out::println);

    }

    @Test
    public void testOpZipWith() {

        Flux.just("a", "b")
                .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s_%s", s1, s2))
                .subscribe(System.out::println);
    }

    @Test
    public void testTake() {
        Flux.range(1, 1000)
                .take(10)
                .subscribe(System.out::print);

    }
}
