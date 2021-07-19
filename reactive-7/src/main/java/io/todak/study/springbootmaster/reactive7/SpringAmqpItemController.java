package io.todak.study.springbootmaster.reactive7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;

@RestController
public class SpringAmqpItemController {

    private static final Logger log =
            LoggerFactory.getLogger(SpringAmqpItemController.class);

    private final AmqpTemplate template;

    public SpringAmqpItemController(AmqpTemplate template) {
        this.template = template;
    }


    @PostMapping("/items")
    Mono<ResponseEntity<?>> addNewItemUsingSpringAmqp(@RequestBody Mono<Item> item) {
        return item
                .subscribeOn(Schedulers.boundedElastic())
                // AmpqTemplate은 블로킹 API를 호출한다.
                // 따라서, subscribeOn() 을 통해, 바운디드 엘라스틱 스케줄러에서 관리하는
                // 별도의 스레드에서 실행되게 만든다.
                .flatMap(content -> Mono.fromCallable(() -> {
                    this.template.convertAndSend("hacking-spring-boot", "new-items-spring-amqp", content);
                    return ResponseEntity.created(URI.create("/items")).build();
                }));
    }

}