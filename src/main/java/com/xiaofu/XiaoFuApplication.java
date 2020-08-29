package com.xiaofu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author yang-o_o 2020-08-24 14:00
 */
@SpringBootApplication
@MapperScan(annotationClass = Repository.class, basePackages = { "com.xiaofu.dao" })
@EnableSwagger2
public class XiaoFuApplication {

    public static void main(String[] args) {
        SpringApplication.run(XiaoFuApplication.class, args);
    }

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("fu")
                .apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.xiaofu.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("xiaofu", "http://xxx.xxx",
                "yangquanxyz@gmail.com");

        return new ApiInfoBuilder().title("fu").description("fu")
                .contact(contact).version("1.0").build();
    }

}
