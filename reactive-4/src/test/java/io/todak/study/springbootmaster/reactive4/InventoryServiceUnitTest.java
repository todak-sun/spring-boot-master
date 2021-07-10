package io.todak.study.springbootmaster.reactive4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class InventoryServiceUnitTest {

    InventoryService inventoryService;

    /**
     * InventoryService는 ItemRepository와 CartRepository를
     * 협력 객체로 한다. 따라서, MockBean으로 주입해 테스트.
     */

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private CartRepository cartRepository;


    @BeforeEach
    void setUp() {
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

        when(cartRepository.findById(anyString())).thenReturn(Mono.empty());

        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class))).thenReturn(Mono.just(sampleCart));

        inventoryService = new InventoryService(itemRepository, cartRepository);
    }


    @Test
    void addItemToEmptyCartShouldProduceOneCartItem() {
        inventoryService.addItemToCart("My Cart", "item1")
                .as(StepVerifier::create)
                // 테스트 대상 메소드의 반환 타입인 Mono<Cart>를 리액터 테스트 모듈의 정적 메소드인 StepVerifier.create()에
                // 메서드 레퍼런스로 연결해, 테스트 기능을 전담하는 리액터 타입 핸들러를 생성한다.
                .expectNextMatches(cart -> {
                    assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);

                    assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));

                    return true;
                })
                .verifyComplete();
        // 마지막으로 verifyComplete를 통해, 리액트의 플로우(Flow)가 성공적으로 완료되었음을 검증.
    }


    @Test
    void alternativeWayToTest() {
        StepVerifier.create(
                inventoryService.addItemToCart("My Cart", "item1")) // < 이러한 방식은, StemVerifier의 인자까지 뒤져봐야하므로, 의도가 무엇인지 알기 어렵다.
                .expectNextMatches(cart -> {
                    assertThat(cart.getCartItems()).extracting(CartItem::getQuantity)
                            .containsExactlyInAnyOrder(1);
                    assertThat(cart.getCartItems()).extracting(CartItem::getItem)
                            .containsExactly(new Item("item1", "TV tray", "Alf TV tray", 19.99));

                    return true;
                })
                .verifyComplete();
    }


}