package io.todak.study.springbootmaster.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class Item {

    @Id
    private String id;
    private String name;
    private double price;

    private Item() {
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

}
