package io.todak.study.springbootmaster.reactive4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class BlockHoundIntegrationTest {

    AltInventoryService inventoryService;

    @MockBean
    ItemRepository itemRepository;
    @MockBean
    CartRepository cartRepository;


    @BeforeEach
    void setUp() {

        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));


        when(cartRepository.findById(anyString())) //
                .thenReturn(Mono.<Cart>empty().hide()); // <3>

        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

        inventoryService = new AltInventoryService(itemRepository, cartRepository);
    }


    @Test
    void blockHoundShouldTrapBlockingCall() {
        Mono.delay(Duration.ofSeconds(1))
                //블로킹 여부를 블록하운드로 검증하기 위해서는,
                //리액터 스레드 안에서 실행되야 한다.
                // 따라서, Mono.delay()를 실행해
                // 후속 작업이 리액터 스레드 안에서 실행하게끔 한다.
                .flatMap(tick -> inventoryService.addItemToCart("My Cart", "item1"))
                .as(StepVerifier::create)
                .verifyErrorSatisfies(throwable -> {
                    assertThat(throwable).hasMessageContaining(
                            "block()/blockFirst()/blockLast() are blocking");
                });
    }
}