package io.todak.study.springbootmaster.web;

import io.todak.study.springbootmaster.domain.Cart;
import io.todak.study.springbootmaster.repository.CartRepository;
import io.todak.study.springbootmaster.repository.ItemRepository;
import io.todak.study.springbootmaster.service.CartService;
import io.todak.study.springbootmaster.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping
@Controller
public class HomeController {

    private final CartService cartService;
    private final InventoryService inventoryService;

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;


    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(
                Rendering.view("home")
                        .modelAttribute("items", this.itemRepository.findAll())
                        .modelAttribute("cart", this.cartRepository.findById("My Cart").defaultIfEmpty(new Cart("My Cart")))
                        .build()
        );
    }

    @GetMapping("/search")
    Mono<Rendering> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam boolean useAnd) {

        return Mono.just(
                Rendering.view("home")
                        .modelAttribute("results", inventoryService.searchByExample(name, description, useAnd))
                        .build()
        );
    }

    @PostMapping("/add/{id}")
    Mono<String> addToCart(@PathVariable String id) {
        return cartService.addToCart("My Cart", id)
                .thenReturn("redirect:/");
    }

}
