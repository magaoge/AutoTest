package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * Created by mgg on 2020/5/30
 */

@SpringBootApplication
@ComponentScan("com.example")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
