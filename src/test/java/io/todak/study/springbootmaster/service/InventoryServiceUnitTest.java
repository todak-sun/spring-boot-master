package io.todak.study.springbootmaster.service;

import io.todak.study.springbootmaster.domain.Cart;
import io.todak.study.springbootmaster.domain.CartItem;
import io.todak.study.springbootmaster.domain.Item;
import io.todak.study.springbootmaster.repository.CartRepository;
import io.todak.study.springbootmaster.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class InventoryServiceUnitTest {

    InventoryService inventoryService;

    @MockBean
    private ItemRepository itemRepository;
    @MockBean
    private CartRepository cartRepository;

    @BeforeEach
    public void setUp() {
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        CartItem sampleCartItem = new CartItem(sampleItem);
        Cart sampleCart = new Cart("My Cart", Collections.singletonList(sampleCartItem));

        // 협력자와의 상호작용 적의
        when(cartRepository.findById(anyString())).thenReturn(Mono.empty());
        when(itemRepository.findById(anyString())).thenReturn(Mono.just(sampleItem));
        when(cartRepository.save(any(Cart.class)).thenReturn(Mono.just(sampleCart)));

//        inventoryService = new InventoryService(itemRepository, cartRepository);
    }

}