package cn.vivian;

import com.google.common.util.concurrent.RateLimiter;

public class RedisLimitMain {
    
    public static void main(String[] args) {
        RateLimiter r = RateLimiter.create(2);
        while(true) {
            System.out.println(r.acquire());
        }
    }
}