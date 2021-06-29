package io.todak.study.springbootmaster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class SpringBootMasterApplication {

    public static void main(String[] args) {

        BlockHound.builder()
                .allowBlockingCallsInside(TemplateEngine.class.getCanonicalName(), "process")
                .install();

        SpringApplication.run(SpringBootMasterApplication.class, args);
    }

}
