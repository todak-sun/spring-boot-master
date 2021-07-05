package io.todak.study.springbootmaster.reactive2;

public class Sample1 {
    void demo1() {
        Cart cart = new Cart("My Cart");
        boolean found = false;

        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getItem().getId().equals("5")) {
                found = true;
            }
        }
        if (found) {
        } else {
        }
    }

    void demo2() {
        Cart cart = new Cart("My Cart");

        if (cart.getCartItems().stream()
                .anyMatch(cartItem -> cartItem.getItem().getId().equals("5"))) {
        } else {
        }
    }
}