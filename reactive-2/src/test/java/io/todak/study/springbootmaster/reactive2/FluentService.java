package io.todak.study.springbootmaster.reactive2;

import org.springframework.data.mongodb.core.ReactiveFluentMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class FluentService {

	private final ReactiveFluentMongoOperations fluentMongoOperations;

	public FluentService(ReactiveFluentMongoOperations fluentMongoOperations) {
		this.fluentMongoOperations = fluentMongoOperations;
	}

	Flux<Item> searchFluently(String name, String description) {
		return fluentMongoOperations.query(Item.class) //
				.matching(query(where("name").is(name).and("description").is(description))) //
				.all();
	}
}