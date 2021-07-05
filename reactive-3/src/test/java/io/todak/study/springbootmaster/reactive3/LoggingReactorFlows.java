package io.todak.study.springbootmaster.reactive3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

class LoggingReactorFlows {

    private static final Logger log = LoggerFactory.getLogger(LoggingReactorFlows.class);

    private ItemRepository itemRepository;

    LoggingReactorFlows(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    Mono<Double> findPriceByMethodReference(String id) {
        return itemRepository.findById(id)
                .map(Item::getPrice);
    }

    Mono<Double> findPriceWithLogging(String id) {
        return itemRepository.findById(id)
                .map(item -> {
                    log.debug("Found item");
                    return item.getPrice();
                });
    }

    Mono<Double> findPriceWithReactorLogging(String id) {
        return itemRepository.findById(id)
                .log("Found item")
                .map(Item::getPrice);
    }
}