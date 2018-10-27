package cn.vivian;

import com.google.common.eventbus.Subscribe;

/**
 * @author swzhang
 * @date 2018/9/13
 * @description
 */
public class DataObserver1 {

    @Subscribe
    public void func1(String msg) {
        System.out.println("String msg = " + msg);
    }

}
