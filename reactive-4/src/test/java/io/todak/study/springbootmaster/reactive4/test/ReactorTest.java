package io.todak.study.springbootmaster.reactive4.test;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ReactorTest {

    static final Logger log = LoggerFactory.getLogger(ReactorTest.class);

    public Mono<String> emptyMono() {
        return Mono.empty();
    }

    @Test
    public void when_mono_is_empty() {
        emptyMono()
                .mapNotNull((string) -> {
                    log.info("map : {}", string);
                    return string;
                })
                .switchIfEmpty(Mono.just("empty"))
                .as(StepVerifier::create)
                .expectNextMatches((string) -> {
                    assertEquals(string, "empty");
                    return true;
                })
                .verifyComplete();


        String justData = emptyMono()
                .map((string) -> {
                    log.info("map : {}", string);
                    return string;
                })
                .block();
        log.info("?? : {}", justData);
//                .as(StepVerifier::create)
//                .expectNextMatches((string) -> {
//                    assertEquals(string, "justData");
//                    return true;
//                })
//                .verifyComplete();
    }

    @Test
    public void test2(){

    }

}
