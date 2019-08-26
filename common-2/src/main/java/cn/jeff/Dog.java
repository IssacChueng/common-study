package cn.jeff;

import cn.jeff.Animal;

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
