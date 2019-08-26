package cn.jeff.cloudstudyredislock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author swzhang
 * @date 2018/8/31
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisData {
    private String name;
    private Integer age;
}
