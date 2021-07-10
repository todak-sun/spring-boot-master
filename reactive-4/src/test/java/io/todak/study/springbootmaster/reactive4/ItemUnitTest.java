package io.todak.study.springbootmaster.reactive4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ItemUnitTest {

    @Test
    void itemBasicsShouldWork() {
        // 도메인 객체가 제공하는 모든 메서드를 테스트하자.
        Item sampleItem = new Item("item1", "TV tray", "Alf TV tray", 19.99);

        assertThat(sampleItem.getId()).isEqualTo("item1");
        assertThat(sampleItem.getName()).isEqualTo("TV tray");
        assertThat(sampleItem.getDescription()).isEqualTo("Alf TV tray");
        assertThat(sampleItem.getPrice()).isEqualTo(19.99);

        assertThat(sampleItem.toString()).isEqualTo("Item{id='item1', name='TV tray', description='Alf TV tray', price=19.99}");

        Item sampleItem2 = new Item("item1", "TV tray", "Alf TV tray", 19.99);
        assertThat(sampleItem).isEqualTo(sampleItem2);
    }
}