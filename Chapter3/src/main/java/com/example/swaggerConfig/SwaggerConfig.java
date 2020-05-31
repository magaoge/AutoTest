package com.example.swaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by mgg on 2020/5/31
 */

//spring boot加载配置文件的注解
@Configuration
//构建swaggerUI的注解
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    //在一个方法上使用@Bean注解就表明这个方法需要交给Spring进行管理
    //注：以下的方法都是固定写法
    public Docket api(){
        /**
         * 1.首先声明文档生成的类型
         * 2.api文档内容信息（这个类是下面自己写的）
         * 3.文档生成后访问的路径
         * 4、5.选择，选择器路径，regex正则匹配，因为目前我们的方路径没有什么
         * 相似的地方，所以这里写"/.*"全部匹配
         * 6.选择构建
         * */
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    /**
     * 1.声明文档标题
     * 2.声明创建人和创建人联系方式
     * 3.针对文档的大概描述
     * 4.文档版本号，一般企业版本号都是四位
     * 5.选择构建
     * */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("我的接口文档")
                .contact(new Contact("mgg","","849189131@qq.com"))
                .description("这是我的swaggerUI生成的接口文档")
                .version("1.0.0.0")
                .build();
    }
}
