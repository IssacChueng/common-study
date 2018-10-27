package cn.vivian.cloudstudyconsumer;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestData {
    private String name;

    private String age;
}
