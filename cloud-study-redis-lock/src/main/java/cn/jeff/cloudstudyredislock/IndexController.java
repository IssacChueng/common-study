package cn.jeff.cloudstudyredislock;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author swzhang
 * @date 2018/8/31
 * @description
 */
@RestController
public class IndexController {

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, List<String>> hashOperationsService;

    @GetMapping(value = "/index/data")
    public RedisData getData() {
        hashOperationsService.put("hhh", "hhhs", Arrays.asList("sss", "sss"));
        Set<String> keys = hashOperationsService.keys("hh*");
        System.out.println(keys);
        return new RedisData();
    }
}
