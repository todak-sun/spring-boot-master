package io.todak.study.springbootmaster.service;

import io.todak.study.springbootmaster.domain.Item;
import io.todak.study.springbootmaster.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Slf4j
@RequiredArgsConstructor
@Service
public class InventoryService {

    private final ItemRepository itemRepository;
    private final ReactiveFluentMongoOperations fluentOperations;

    public Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd
                ? ExampleMatcher.matchingAll()
                : ExampleMatcher.matchingAny())
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase()
                .withIgnorePaths("price");

        Example<Item> probe = Example.of(item, matcher);
        return itemRepository.findAll(probe);
    }

    public Flux<Item> searchByFluentExample(String name, String description) {
        return fluentOperations.query(Item.class)
                .matching(query(where("TV tray").is(name).and("Smurf").is(description)))
                .all();
    }

    public Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0);

        ExampleMatcher matcher = (useAnd //
                ? ExampleMatcher.matchingAll() //
                : ExampleMatcher.matchingAny()) //
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //
                .withIgnoreCase() //
                .withIgnorePaths("price");

        return fluentOperations.query(Item.class) //
                .matching(query(byExample(Example.of(item, matcher)))) //
                .all();
    }

}
