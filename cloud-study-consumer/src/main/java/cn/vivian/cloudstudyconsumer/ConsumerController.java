package cn.vivian.cloudstudyconsumer;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author swzhang
 * @date 2018/8/31
 * @description
 */
@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    @Resource
    private ConsumerService consumerService;

    @GetMapping(value = "/ribbon", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public RestData restData() {
        RestData body = consumerService.consumeService();
        return body;
    }
}
