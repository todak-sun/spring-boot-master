package io.todak.study.springbootmaster.reactive2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
class InventoryController {

    private InventoryService service;

    InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/items", produces = "application/stream+json")
    Flux<Item> findInventoryData(@RequestParam("q") String q) {
        return this.service.getItems()
                .filter(item -> item.getName().contains(q));
    }
}