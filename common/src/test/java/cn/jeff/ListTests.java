package cn.jeff;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author swzhang
 * @date 2019/1/22
 * @description
 */
public class ListTests {
    @Test
    public void test() {
        List<String> list = new ArrayList<>();
        list.add("s");
        list.add("s");
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println(list);
    }
}
