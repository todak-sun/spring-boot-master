package io.todak.study.springbootmaster.repository;

import io.todak.study.springbootmaster.domain.Item;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface ItemByExampleRepository extends ReactiveQueryByExampleExecutor<Item> {

}
