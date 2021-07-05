package io.todak.study.springbootmaster.reactive2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CartRepositoryTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void is_bean(){
        CartRepository bean = context.getBean(CartRepository.class);
        assertNotNull(bean);
    }

}