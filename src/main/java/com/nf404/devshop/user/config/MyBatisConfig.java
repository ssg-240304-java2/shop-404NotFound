package com.nf404.devshop.user.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.nf404.devshop", annotationClass = Mapper.class)
public class MyBatisConfig {
}
