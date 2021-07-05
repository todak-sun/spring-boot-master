package io.todak.study.springbootmaster.reactive2;

import org.springframework.data.repository.CrudRepository;

interface BlockingItemRepository extends CrudRepository<Item, String> {

}