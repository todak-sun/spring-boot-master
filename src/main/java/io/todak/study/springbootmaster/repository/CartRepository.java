package io.todak.study.springbootmaster.repository;

import io.todak.study.springbootmaster.domain.Cart;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CartRepository extends ReactiveCrudRepository<Cart, String> {
}
