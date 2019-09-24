package edu.xpu.buckmoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Maven的配置问题导致的工程导入出错
@SpringBootApplication
public class BuckmooApplication {
    public static void main(String[] args) {
        SpringApplication.run(BuckmooApplication.class, args);
    }
}
