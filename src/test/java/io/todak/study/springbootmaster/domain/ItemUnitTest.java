package io.todak.study.springbootmaster.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemUnitTest {

    @Test
    void itemBasicsShouldWork() {
        String id = "item1";
        String name = "TV tray";
        String description = "Alf TV tray";
        double price = 19.99;

        Item sampleItem = new Item(id, name, description, price);

        assertThat(sampleItem.getId()).isEqualTo(id);
        assertThat(sampleItem.getName()).isEqualTo(name);
        assertThat(sampleItem.getDescription()).isEqualTo(description);
        assertThat(sampleItem.getPrice()).isEqualTo(price);

        assertThat(sampleItem.toString())
                .isEqualTo("Item(id=item1, name=TV tray, description=Alf TV tray, price=19.99)");

        Item sampleItem2 = new Item(id, name, description, price);
        assertThat(sampleItem).isEqualTo(sampleItem2);

    }


}