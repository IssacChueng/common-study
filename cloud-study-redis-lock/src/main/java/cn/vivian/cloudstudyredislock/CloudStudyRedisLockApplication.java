package cn.vivian.cloudstudyredislock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CloudStudyRedisLockApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStudyRedisLockApplication.class, args);
    }
}
