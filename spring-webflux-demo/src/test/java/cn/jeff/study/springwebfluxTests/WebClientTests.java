package cn.jeff.study.springwebfluxTests;

import org.junit.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author swzhang
 * @date 2019/08/29
 */
public class WebClientTests {

    @Test
    public void testClient() {
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<Integer> integerMono = webClient.get()
                .uri("calculator?operator={operator}", Map.of("operator", "add"))
                .exchange()
                .flatMap(respo ->
                        respo.bodyToMono(Integer.class)
                );
        System.out.println(integerMono.block());
    }
}
