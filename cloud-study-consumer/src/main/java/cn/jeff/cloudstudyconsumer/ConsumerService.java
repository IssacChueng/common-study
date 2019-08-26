package cn.jeff.cloudstudyconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class ConsumerService {
    @Resource
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public RestData consumeService() {
        return restTemplate.getForEntity("http://CLOUD-SERVICE/index/data", RestData.class).getBody();
    }

    public RestData helloFallback() {
        return RestData.builder().name("error").build();
    }
}
