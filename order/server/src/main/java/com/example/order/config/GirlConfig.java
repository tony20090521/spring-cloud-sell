package com.example.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by 廖师兄
 * 2018-02-03 22:25
 */
@Data
@Component
@ConfigurationProperties(prefix = "girl")
@RefreshScope
public class GirlConfig {

    private Integer age;
    private String name;
}
