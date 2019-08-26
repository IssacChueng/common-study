package cn.jeff.springcloudstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SpringCloudStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudStudyApplication.class, args);
    }
}
