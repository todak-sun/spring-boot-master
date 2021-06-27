package io.todak.study.springbootmaster.web;

import io.todak.study.springbootmaster.domain.Cart;
import io.todak.study.springbootmaster.domain.CartItem;
import io.todak.study.springbootmaster.repository.CartRepository;
import io.todak.study.springbootmaster.repository.ItemRepository;
import io.todak.study.springbootmaster.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@RequestMapping
@Controller
public class HomeController {

    private final CartService cartService;

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public HomeController(CartService cartService, ItemRepository itemRepository, CartRepository cartRepository) {
        this.cartService = cartService;
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }


    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(
                Rendering.view("home")
                        .modelAttribute("items", this.itemRepository.findAll())
                        .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                        .build()
        );
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

}
