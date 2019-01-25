package com.wayne.partone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.wayne.partone.partone.controller"})
public class SwaggerConfig {

    private String title;
    private String description;
    private String version;

    private String name;
    private String url;
    private String email;

    Contact contact = new Contact("wayne", "", "1032928726@qq.com");
    ApiInfo apiInfo = new ApiInfoBuilder().contact(contact).title("测试")
            .description("测试")
            .version("1.1.0")
            .build();
    @Bean
    public Docket customDocket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo);
    }

}
