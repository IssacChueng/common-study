package cn.vivian;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DataObserver1 dataObserver1 = new DataObserver1();
        IntegerObserver integerObserver = new IntegerObserver();
        EventBusCenter.register(dataObserver1);
        EventBusCenter.register(integerObserver);

        System.out.println("=============== start ==================");
        EventBusCenter.post("post String method");
        EventBusCenter.post(123);

        System.out.println("=============== after unregister ===================");

        //注销integer
        EventBusCenter.unregister(integerObserver);
        EventBusCenter.post("post String method");
        EventBusCenter.post(123);

        System.out.println("================ end =======================");

    }
}
