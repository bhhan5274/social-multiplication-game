package com.bhhan.gateway;

import com.bhhan.gateway.config.RibbonConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by hbh5274@gmail.com on 2020-07-15
 * Github : http://github.com/bhhan5274
 */

@EnableZuulProxy
@SpringBootApplication
@EnableEurekaClient
@RibbonClients(defaultConfiguration = RibbonConfiguration.class)
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
