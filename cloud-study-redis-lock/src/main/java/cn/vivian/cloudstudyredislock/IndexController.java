package cn.vivian.cloudstudyredislock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author swzhang
 * @date 2018/8/31
 * @description
 */
@RestController
public class IndexController {
    @GetMapping(value = "/index/data")
    public RedisData getData() {
        RedisData data = RedisData.builder().name("zsw").age(25).build();
        return data;
    }
}
