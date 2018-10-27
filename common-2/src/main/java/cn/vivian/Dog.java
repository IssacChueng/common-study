package cn.vivian;

import cn.vivian.Animal;

/**
 * @author swzhang
 * @date 2018/7/25
 * @description
 */
public class Dog implements Animal {
    @Override
    public void eat() {
        System.out.println("Dog eating");
    }
}
