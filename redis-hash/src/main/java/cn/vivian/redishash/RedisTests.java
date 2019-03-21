package cn.vivian.redishash;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author swzhang
 * @date 2018/11/6
 * @description
 */
@Component
public class RedisTests {

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, List<String>> hashOperations;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void init() {
        hashOperations.put("hhhh", "hhhh", Arrays.asList("ssss", "ssss"));
        hashOperations.put("hhh2", "hhh", Arrays.asList("sss", "sss"));
        Set<String> keys = redisTemplate.keys("hhh*");
        System.out.println(keys);

        if (keys != null) {
            redisTemplate.delete(keys);
        }
        keys = redisTemplate.keys("hhh*");
        System.out.println(keys);

    }
}
