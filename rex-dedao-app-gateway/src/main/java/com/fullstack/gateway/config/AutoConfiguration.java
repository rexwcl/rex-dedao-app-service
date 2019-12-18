package com.fullstack.gateway.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fullstack.gateway.gen.SnowflakeIdGenerator;


@Configuration
@EnableConfigurationProperties({IdGenProperties.class})
public class AutoConfiguration {
	
    /**
     * ID生成器配置
     *
     * @param properties
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(IdGenProperties.class)
    public SnowflakeIdGenerator snowflakeIdWorker(IdGenProperties properties) {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(properties.getWorkId(), properties.getCenterId());
        System.out.println("bean ["+ snowflakeIdGenerator +"] properties ["+ properties +"]");
        return snowflakeIdGenerator;
    }


}
