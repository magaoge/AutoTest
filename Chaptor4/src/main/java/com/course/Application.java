package com.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;

/**
 * Created by mgg on 2020/6/1
 */

/**开启定时任务，自动扫描，相当于@ComponentScan("com.example")
 * 但是@ComponentScan只注入设置的类或者包及包的子集对象。这会导致原来@SpringBootApplication 自动配置装配的功能在对象注入的时候不会注入当前工程。
   */
@EnableScheduling
@SpringBootApplication
public class Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class,args);
    }

    //预摧毁
    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
