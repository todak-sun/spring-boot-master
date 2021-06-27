package io.todak.study.springbootmaster.domain;

import lombok.Getter;

@Getter
public class CartItem {

    private Item item;
    private int quantity;

    private CartItem() {
    }

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1;
    }

    public void increment(){
        ++this.quantity;
    }

}
