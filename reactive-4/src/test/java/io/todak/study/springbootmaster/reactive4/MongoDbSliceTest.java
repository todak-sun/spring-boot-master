package io.todak.study.springbootmaster.reactive4;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest //몽고 DB 테스트를 위해 붙인 어노테이션
public class MongoDbSliceTest {

    @Autowired
    ItemRepository repository;

    @Test
    void itemRepositorySavesItems() {
        Item sampleItem = new Item(
                "name", "description", 1.99);

        repository.save(sampleItem)
                .as(StepVerifier::create)
                .expectNextMatches(item -> {
                    assertThat(item.getId()).isNotNull();
                    assertThat(item.getName()).isEqualTo("name");
                    assertThat(item.getDescription()).isEqualTo("description");
                    assertThat(item.getPrice()).isEqualTo(1.99);
                    return true;
                })
                .verifyComplete();
    }
}