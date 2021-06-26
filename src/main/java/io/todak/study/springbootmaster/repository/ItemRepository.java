package io.todak.study.springbootmaster.repository;

import io.todak.study.springbootmaster.domain.Item;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}
