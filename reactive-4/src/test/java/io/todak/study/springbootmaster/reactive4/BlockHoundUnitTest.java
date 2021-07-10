package io.todak.study.springbootmaster.reactive4;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


class BlockHoundUnitTest {

    public static final Logger log = LoggerFactory.getLogger(BlockHoundUnitTest.class);

    @Test
    void threadSleepIsABlockingCall() {
        BlockHound.builder().blockingMethodCallback((method) -> {
            log.info("method name : {}", method.getName());
        });

        Mono.delay(Duration.ofSeconds(1))
                .flatMap(tick -> {
                    try {
                        Thread.sleep(10);
                        return Mono.just(true);
                    } catch (InterruptedException e) {
                        return Mono.error(e);
                    }
                }) //
                .as(StepVerifier::create) //
//                .verifyComplete();
                .verifyErrorMatches(throwable -> {
                    assertThat(throwable.getMessage()) //
                            .contains("Blocking call! java.lang.Thread.sleep");
                    return true;
                });

    }

}
