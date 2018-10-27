package cn.vivian;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ServiceLoader<Animal> serviceLoader = ServiceLoader.load(Animal.class);
        Iterator<Animal> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            Animal animal = iterator.next();
            animal.eat();
        }
    }
}
