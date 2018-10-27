package cn.vivian;

import com.google.common.eventbus.Subscribe;

/**
 * @author swzhang
 * @date 2018/9/13
 * @description
 */
public class IntegerObserver {

    @Subscribe
    public void func(Integer msg) {
        System.out.println("Integer msg = " + msg);
    }
}
