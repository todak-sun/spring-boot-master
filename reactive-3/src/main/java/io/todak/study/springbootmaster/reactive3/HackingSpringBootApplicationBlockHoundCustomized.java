package io.todak.study.springbootmaster.reactive3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.thymeleaf.TemplateEngine;
import reactor.blockhound.BlockHound;

@SpringBootApplication
public class HackingSpringBootApplicationBlockHoundCustomized {


    public static void main(String[] args) {
        BlockHound.builder()
                .allowBlockingCallsInside(
                        TemplateEngine.class.getCanonicalName(), "process")
                // 블로킹을 일으키는 TemplateEngine.process를 제외하고, 나머지에 대해 블로킹 검사 수행
                .install();
        SpringApplication.run(HackingSpringBootApplicationBlockHoundCustomized.class, args);
    }
}