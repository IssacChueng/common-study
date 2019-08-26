package cn.jeff.commontoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class CommonTokenApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonTokenApplication.class, args);
    }
}
