package io.todak.study.springbootmaster.reactive1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;


@RequestMapping
@Controller
public class HomeController {

    @GetMapping
    Mono<String> home() {
        return Mono.just("home");
    }

}
