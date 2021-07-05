package io.todak.study.springbootmaster.reactive2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomeController2 {

    private final CartService cartService;

    public HomeController2(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return this.cartService.addToCart("My Cart", id) //
            .thenReturn("redirect:/");
    }



}