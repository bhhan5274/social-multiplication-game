package com.bhhan.gateway.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import org.springframework.context.annotation.Bean;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */
public class RibbonConfiguration {

    @Bean
    public IPing ribbonPing(final IClientConfig config){
        return new PingUrl(false, "/actuator/health");
    }

    @Bean
    public IRule ribbonRule(final IClientConfig config){
        return new AvailabilityFilteringRule();
    }
}
