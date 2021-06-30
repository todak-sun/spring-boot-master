package io.todak.study.springbootmaster.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@EqualsAndHashCode
@ToString
@Getter
public class Item {

    @Id
    private String id;
    private String name;
    private String description;
    private double price;
//    private String distributorRegion;
//    private LocalDateTime releaseDate;
//    private int availableUnits;
//    private Point location;
//    private boolean active;

    private Item() {
    }

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Item(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Item(String id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
