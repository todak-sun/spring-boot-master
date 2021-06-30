package io.todak.study.springbootmaster.reactive2;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.byExample;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
class InventoryService {

    private ItemRepository repository;
    private ReactiveFluentMongoOperations fluentOperations;

    InventoryService(ItemRepository repository, //
                     ReactiveFluentMongoOperations fluentOperations) {
        this.repository = repository;
        this.fluentOperations = fluentOperations;
    }

    Flux<Item> getItems() {
        // imagine calling a remote service!
        return Flux.empty();
    }

    // tag::code-2[]
    Flux<Item> search(String partialName, String partialDescription, boolean useAnd) {
        if (partialName != null) {
            if (partialDescription != null) {
                if (useAnd) {
                    return repository //
                            .findByNameContainingAndDescriptionContainingAllIgnoreCase( //
                                    partialName, partialDescription);
                } else {
                    return repository.findByNameContainingOrDescriptionContainingAllIgnoreCase( //
                            partialName, partialDescription);
                }
            } else {
                return repository.findByNameContaining(partialName);
            }
        } else {
            if (partialDescription != null) {
                return repository.findByDescriptionContainingIgnoreCase(partialDescription);
            } else {
                return repository.findAll();
            }
        }
    }
    // end::code-2[]

    // tag::code-3[]
    Flux<Item> searchByExample(String name, String description, boolean useAnd) {
        Item item = new Item(name, description, 0.0); // <1>

        ExampleMatcher matcher = (useAnd // <2>
                ? ExampleMatcher.matchingAll() //
                : ExampleMatcher.matchingAny()) //
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) // <3>
                .withIgnoreCase() // <4>
                .withIgnorePaths("price"); // <5>

        Example<Item> probe = Example.of(item, matcher); // <6>

        return repository.findAll(probe); // <7>
    }
    // end::code-3[]

    // tag::code-4[]
    Flux<Item> searchByFluentExample(String name, String description) {
        return fluentOperations.query(Item.class) //
                .matching(query(where("TV tray").is(name).and("Smurf").is(description))) //
                .all();
    }
    // end::code-4[]

    // tag::code-5[]
    Flux<Item> searchByFluentExample(String name, String description, boolean useAnd) {
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